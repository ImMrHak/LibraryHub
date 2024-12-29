package com.libraryhub.msusers.application.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryhub.msusers.application.user.mapper.UserMapper;
import com.libraryhub.msusers.application.user.record.request.CreateUserDTO;
import com.libraryhub.msusers.application.user.record.request.DeleteUserDTO;
import com.libraryhub.msusers.application.user.record.request.RecoverUserDTO;
import com.libraryhub.msusers.application.user.record.request.UpdateUserDTO;
import com.libraryhub.msusers.application.user.record.response.DataUserDTO;
import com.libraryhub.msusers.domain.user.model.User;
import com.libraryhub.msusers.domain.user.service.UserDomainService;
import com.libraryhub.msusers.infrastructure.bookOF.books.BooksExternalService;
import com.libraryhub.msusers.infrastructure.bookOF.books.record.response.DataBookDTO;
import com.libraryhub.msusers.infrastructure.borrowOF.borrows.BorrowExternalService;
import com.libraryhub.msusers.infrastructure.borrowOF.borrows.record.response.DataBorrowDTO;
import com.libraryhub.msusers.infrastructure.reservationOF.reservations.ReservationExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDomainService userDomainService;
    private final UserMapper userMapper;

    private final BorrowExternalService borrowExternalService;
    private final ReservationExternalService reservationExternalService;
    private final BooksExternalService booksExternalService;

    @Override
    public Object createUser(CreateUserDTO createUserDto) {
        if(userDomainService.existsByUsername(createUserDto.username())) return "User already exists";

        User dbUser = userMapper.userRequestDTOToUser(createUserDto);

        dbUser.setIsDeleted(false);

        return userMapper.userToDataUserDTO(userDomainService.saveUser(dbUser));
    }

    @Override
    public Object updateUser(UpdateUserDTO updateUserDTO) {
        if(!userDomainService.existsUserById(updateUserDTO.idUser())) return "User does not exist";

        User dbUser = userDomainService.findUserById(updateUserDTO.idUser());
        if(dbUser == null) return "User does not exist";

        if(dbUser.getIsDeleted()) return "User does not exist because it's deleted";

        dbUser.setUsername(updateUserDTO.username());
        dbUser.setFirstName(updateUserDTO.firstName());
        dbUser.setLastName(updateUserDTO.lastName());
        dbUser.setEmail(updateUserDTO.email());
        dbUser.setUserType(updateUserDTO.userType());
        return userMapper.userToDataUserDTO(userDomainService.saveUser(dbUser));
    }

    @Override
    public Object deleteUser(DeleteUserDTO deleteUserDTO) {
        if(!userDomainService.existsUserById(deleteUserDTO.idUser())) return "User does not exist";

        User dbUser = userDomainService.findUserById(deleteUserDTO.idUser());

        if(dbUser.getIsDeleted()) return "User is already deleted";

        dbUser.setIsDeleted(true);

        return userDomainService.deleteUser(dbUser);
    }

    @Override
    public Object recoverUser(RecoverUserDTO recoverUserDTO) {
        if(!userDomainService.existsUserById(recoverUserDTO.idUser())) return "User does not exist";
        User dbUser = userDomainService.findUserById(recoverUserDTO.idUser());
        if(dbUser.getIsDeleted()) return "User is already deleted";
        dbUser.setIsDeleted(false);

        userDomainService.saveUser(dbUser);
        return true;
    }

    @Override
    public List<DataUserDTO> getUsers() {
        return userDomainService.findAll().stream().filter(u -> !u.getIsDeleted()).map(userMapper::userToDataUserDTO).collect(Collectors.toList());
    }

    @Override
    public DataUserDTO getUserById(String id) {
        User dbUser = userDomainService.findUserById(id);

        if(dbUser == null) return null;

        if(dbUser.getIsDeleted()) return null;

        return userMapper.userToDataUserDTO(dbUser);
    }

    @Override
    public List<DataUserDTO> getDeletedUsers() {
        return userDomainService.findAll().stream().filter(User::getIsDeleted).map(userMapper::userToDataUserDTO).collect(Collectors.toList());
    }

    @Override
    public DataUserDTO getDeletedUserById(String id) {
        User dbUser = userDomainService.findUserById(id);
        if(!dbUser.getIsDeleted()) return null;
        return userMapper.userToDataUserDTO(dbUser);
    }

    @Override
    public Object getMyTotalDashboardInformation(String id) {

        ObjectMapper objectMapper = new ObjectMapper();

        // Convert the response to Integer
        Integer borrowsCount = objectMapper.convertValue(
                borrowExternalService.getMyBorrowsCount().getBody(), Integer.class);

        Integer reservationsCount = objectMapper.convertValue(
                reservationExternalService.getMyReservationsCount().getBody(), Integer.class);

        Integer returnedBorrowsCount = objectMapper.convertValue(
                borrowExternalService.getMyReturnedBorrowsCount().getBody(), Integer.class);

        // Specify the type for recentReturnedBorrows
        Object responseRecentBorrows = borrowExternalService.recentReturnedBorrows().getBody();
        List<DataBorrowDTO> recentReturnedBorrows = objectMapper.convertValue(
                responseRecentBorrows, new TypeReference<List<DataBorrowDTO>>() {});

        List<DataBookDTO> recentReturnedBooks = new ArrayList<>();

        for (DataBorrowDTO borrow : recentReturnedBorrows) {
            Object responseDataBookDTO = booksExternalService.getBookById(borrow.idBook()).getBody();
            DataBookDTO dataBookDTO = objectMapper.convertValue(responseDataBookDTO, DataBookDTO.class);

            recentReturnedBooks.add(dataBookDTO);
        }

        Map<String, Object> results = new HashMap<>();
        results.put("borrowsCount", borrowsCount);
        results.put("reservationsCount", reservationsCount);
        results.put("returnedBorrowsCount", returnedBorrowsCount);
        results.put("recentReturnedBooks", recentReturnedBooks);

        return results;
    }


}
