package com.libraryhub.msborrows.application;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        if(new Date(System.currentTimeMillis()).after(createBorrowDTO.returnDate())) return "Borrow date must be before return date";

        Object responseBookBody = booksExternalService.getBookById(createBorrowDTO.idBook()).getBody();
        DataBookDTO dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);


        Object responseUserBody = usersExternalService.getUserById(createBorrowDTO.idUser()).getBody();
        DataUserDTO dataUserDTO = new ObjectMapper().convertValue(responseUserBody, DataUserDTO.class);

        if(dataBookDTO == null) return "the book not exist";
        if(dataUserDTO == null) return "the user not exist";
        if(!dataBookDTO.isAvailable()) return "the book not availabale";

        responseBookBody = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dataBookDTO.idBook(), false)).getBody();
        dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);

        if (responseBookBody == null || dataBookDTO.isAvailable()) {
            return "Error updating book availability";
        }

        Borrow borrow = borrowMapper.mapCreateBorrowDTOToBorrow(createBorrowDTO);
        borrow.setBorrowDate(new Date(System.currentTimeMillis()));

        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.saveBorrow(borrow));
    }

    @Override
    public Object updateBorrow(UpdateBorrowDTO updateBorrowDTO) {
        if(!borrowDomainService.existsByIdBorrow(updateBorrowDTO.idBorrow())) return "borrow does not exist";

        Borrow dbBorrow = borrowDomainService.findBorrowById(updateBorrowDTO.idBorrow());
        if(!dbBorrow.getIdUser().equals(updateBorrowDTO.idUser())) return "this is not your borrow";

        if(dbBorrow.getBorrowDate().after(updateBorrowDTO.returnDate())) return "Borrow date must be before return date";

        Object responseBookBody = booksExternalService.getBookById(updateBorrowDTO.idBook()).getBody();
        DataBookDTO dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);

        Object responseUserBody = usersExternalService.getUserById(updateBorrowDTO.idUser()).getBody();
        DataUserDTO dataUserDTO = new ObjectMapper().convertValue(responseUserBody, DataUserDTO.class);

        if(dataBookDTO == null) return "the book not exist";
        if(dataUserDTO == null) return "the user not exist";
        if(!dataBookDTO.isAvailable()) return "the book not availabale";
        if(!Objects.equals(dbBorrow.getIdBook(), dataBookDTO.idBook())){
            booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dbBorrow.getIdBook(), true)).getBody();


            Object responseNewBookBody = booksExternalService.getBookById(updateBorrowDTO.idBorrow()).getBody();
            DataBookDTO dataNewBookDTO = new ObjectMapper().convertValue(responseNewBookBody, DataBookDTO.class);

            dbBorrow.setIdBook(dataNewBookDTO.idBook());
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
    public List<DataBorrowDTO> getBorrows(GetBorrowDTO getBorrowDTO) {
        return borrowDomainService.findAll().stream().filter(b ->b.getIdUser().equals(getBorrowDTO.idUser())).map(borrowMapper::mapBorrowToDataBorrowDTO).collect(Collectors.toList());
    }

    @Override
    public DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO) {
        if(!borrowDomainService.existsByIdBorrow(getBorrowByIdDTO.idBorrow())) return null;
        Borrow dbBorrow = borrowDomainService.findBorrowById(getBorrowByIdDTO.idBorrow());
        if(!dbBorrow.getIdUser().equals(getBorrowByIdDTO.idUser())) return null;

        return borrowMapper.mapBorrowToDataBorrowDTO(dbBorrow);
    }

    @Override
    public DataBorrowDTO getLatestBorrowByIdBook(GetLatestBorrowByIdBookDTO getLatestBorrowByIdBookDTO) {
        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.findFirstByIdBookOrderByReturnDate(getLatestBorrowByIdBookDTO.idBook()));
    }
}
