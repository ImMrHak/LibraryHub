package com.libraryhub.msborrows.application;

import com.libraryhub.msborrows.application.mapper.BorrowMapper;
import com.libraryhub.msborrows.application.record.request.*;
import com.libraryhub.msborrows.application.record.response.DataBorrowDTO;
import com.libraryhub.msborrows.domain.model.Borrow;
import com.libraryhub.msborrows.domain.service.BorrowDomainService;
import com.libraryhub.msborrows.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msborrows.infrastructure.booksOF.books.record.request.UpdateBookAvailabilityDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.books.record.response.DataBookDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.themes.ThemesExternalService;
import com.libraryhub.msborrows.infrastructure.usersOF.users.UsersExternalService;
import com.libraryhub.msborrows.infrastructure.usersOF.users.record.response.DataUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BorrowDomainService borrowDomainService;
    private final BorrowMapper borrowMapper;
    private final BooksExternalService booksExternalService;
    private final ThemesExternalService themesExternalService;
    private final UsersExternalService usersExternalService;

    @Override
    public Object createBorrow(CreateBorrowDTO createBorrowDTO) {
        if(createBorrowDTO.borrowDate().before(createBorrowDTO.returnDate())) return "Borrow date must be before return date";

        DataBookDTO dataBookDTO = (DataBookDTO) booksExternalService.getBookById(createBorrowDTO.idBook()).getBody();
        DataUserDTO dataUserDTO = (DataUserDTO) usersExternalService.getUserById(createBorrowDTO.idUser()).getBody();

        if(dataBookDTO == null) return "the book not exist";
        if(dataUserDTO == null) return "the user not exist";
        if(!dataBookDTO.isAvailable()) return "the book not availabale";

        var response = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dataBookDTO.idBook(), false));
        if (response == null || !(response.getBody() instanceof DataBookDTO updatedBookDTO) || updatedBookDTO.isAvailable()) {
            return "Error updating book availability";
        }

        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.saveBorrow(borrowMapper.mapCreateBorrowDTOToBorrow(createBorrowDTO)));
    }

    @Override
    public Object updateBorrow(UpdateBorrowDTO updateBorrowDTO) {
        if(!borrowDomainService.existsByIdBorrow(updateBorrowDTO.idBorrow())) return "borrow does not exist";

        Borrow dbBorrow = borrowDomainService.findBorrowById(updateBorrowDTO.idBorrow());
        if(!dbBorrow.getIdUser().equals(updateBorrowDTO.idUser())) return "this is not your borrow";

        if(dbBorrow.getBorrowDate().before(updateBorrowDTO.returnDate())) return "Borrow date must be before return date";

        DataBookDTO dataBookDTO = (DataBookDTO) booksExternalService.getBookById(updateBorrowDTO.idBook()).getBody();
        DataUserDTO dataUserDTO = (DataUserDTO) usersExternalService.getUserById(updateBorrowDTO.idUser()).getBody();

        if(dataBookDTO == null) return "the book not exist";
        if(dataUserDTO == null) return "the user not exist";
        if(!dataBookDTO.isAvailable()) return "the book not availabale";

        var response = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dataBookDTO.idBook(), false));
        if (response == null || !(response.getBody() instanceof DataBookDTO updatedBookDTO) || updatedBookDTO.isAvailable()) {
            return "Error updating book availability";
        }

        dbBorrow.setReturnDate(updateBorrowDTO.returnDate());
        dbBorrow.setActualReturnDate(updateBorrowDTO.actualReturnDate());

        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.saveBorrow(dbBorrow));
    }

    @Override
    public Object deleteBorrow(DeleteBorrowDTO deleteBorrowDTO) {
        if(!borrowDomainService.existsByIdBorrow(deleteBorrowDTO.idBorrow())) return "borrow does not exist";

        Borrow dbBorrow = borrowDomainService.findBorrowById(deleteBorrowDTO.idBorrow());

        var response = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dbBorrow.getIdBook(), true));
        if (response == null || !(response.getBody() instanceof DataBookDTO updatedBookDTO) || !updatedBookDTO.isAvailable()) {
            return "Error updating book availability";
        }
        return borrowDomainService.deleteBorrow(dbBorrow);
    }

    @Override
    public List<DataBorrowDTO> getBorrow(GetBorrowDTO getBorrowDTO) {
        return borrowDomainService.findAll().stream().filter(b ->b.getIdUser().equals(getBorrowDTO.IdUser())).map(borrowMapper::mapBorrowToDataBorrowDTO).collect(Collectors.toList());
    }

    @Override
    public DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO) {
        if(!borrowDomainService.existsByIdBorrow(getBorrowByIdDTO.idBorrow())) return null;
        Borrow dbBorrow = borrowDomainService.findBorrowById(getBorrowByIdDTO.idBorrow());
        if(!dbBorrow.getIdUser().equals(getBorrowByIdDTO.IdUser())) return null;

        return borrowMapper.mapBorrowToDataBorrowDTO(dbBorrow);
    }
}
