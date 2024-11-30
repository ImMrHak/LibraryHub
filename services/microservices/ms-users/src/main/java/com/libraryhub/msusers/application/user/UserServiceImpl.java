package com.libraryhub.msusers.application.user;

import com.libraryhub.msusers.application.user.mapper.UserMapper;
import com.libraryhub.msusers.application.user.record.request.CreateUserDTO;
import com.libraryhub.msusers.application.user.record.request.DeleteUserDTO;
import com.libraryhub.msusers.application.user.record.request.RecoverUserDTO;
import com.libraryhub.msusers.application.user.record.request.UpdateUserDTO;
import com.libraryhub.msusers.application.user.record.response.DataUserDTO;
import com.libraryhub.msusers.domain.user.model.User;
import com.libraryhub.msusers.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDomainService userDomainService;
    private final UserMapper userMapper;

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

}
