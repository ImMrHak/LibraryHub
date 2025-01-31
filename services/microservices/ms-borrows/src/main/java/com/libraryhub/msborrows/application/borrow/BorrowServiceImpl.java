package com.libraryhub.msborrows.application.borrow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryhub.msborrows.application.borrow.mapper.BorrowMapper;
import com.libraryhub.msborrows.domain.borrow.model.Borrow;
import com.libraryhub.msborrows.domain.borrow.service.BorrowDomainService;
import com.libraryhub.msborrows.infrastructure.FeignClients.microservices.bookOF.books.BooksExternalService;
import com.libraryhub.msborrows.infrastructure.FeignClients.microservices.usersOF.users.UsersExternalService;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.UpdateBookAvailabilityDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.response.DataBookDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.*;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.response.DataBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msNotifications.notification.record.request.BorrowCreatedEvent;
import com.libraryhub.shareddata.sharedRecords.msUsers.user.record.response.DataUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

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
    private final UsersExternalService usersExternalService;

    private final KafkaTemplate<String, BorrowCreatedEvent> kafkaTemplate;

    private static final String BORROW_CREATED_TOPIC = "borrow-created";


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

        // Send Kafka notification
        BorrowCreatedEvent borrowCreatedEvent =  new BorrowCreatedEvent(
                dataUserDTO.email(),
                "A new borrow has been created successfully!",
                new Date(System.currentTimeMillis())
        );

        kafkaTemplate.send(BORROW_CREATED_TOPIC, borrowCreatedEvent);

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
    public Object deleteBorrow(Authentication authentication, DeleteBorrowDTO deleteBorrowDTO) {
        if(!borrowDomainService.existsByIdBorrow(deleteBorrowDTO.idBorrow())) return "borrow does not exist";

        Borrow dbBorrow = borrowDomainService.findBorrowById(deleteBorrowDTO.idBorrow());

        if(!dbBorrow.getIdUser().equals(((Jwt) authentication.getPrincipal()).getSubject())) return "this is not your borrow";

        var response = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dbBorrow.getIdBook(), true));
        if (response == null || !(response.getBody() instanceof DataBookDTO updatedBookDTO) || !updatedBookDTO.isAvailable()) {
            return "Error updating book availability";
        }
        return borrowDomainService.deleteBorrow(dbBorrow);
    }

    @Override
    public List<DataBorrowDTO> getBorrows() {
        return borrowDomainService.findAll().stream().map(borrowMapper::mapBorrowToDataBorrowDTO).collect(Collectors.toList());
    }

    @Override
    public Boolean existBorrowedBookByISBN(String isbn) {
        Object responseBookBody = booksExternalService.getBookByISBN(isbn).getBody();
        DataBookDTO dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);
        List<Borrow> borrowsWithISBN = borrowDomainService.findAllByIdBookOrderByReturnDateDesc(dataBookDTO.idBook());

        for(Borrow borrow : borrowsWithISBN)  if(new Date(System.currentTimeMillis()).after(borrow.getReturnDate())) return true;

        return false;
    }

    @Override
    public Integer getMyBorrowsCount(GetMyBorrowsDTO getMyBorrowsDTO) {
        return borrowDomainService.countBorrowsByIdUser(getMyBorrowsDTO.idUser());
    }

    @Override
    public Integer getMyReturnedBorrowsCount(GetMyBorrowsDTO getMyBorrowsDTO) {

        return borrowDomainService.countBorrowByIdUserAndActualReturnDateIsNotNull(getMyBorrowsDTO.idUser());
    }


    @Override
    public DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO) {
        if(!borrowDomainService.existsByIdBorrow(getBorrowByIdDTO.idBorrow())) return null;

        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.findBorrowById(getBorrowByIdDTO.idBorrow()));
    }

    @Override
    public DataBorrowDTO getMyBorrowById(GetMyBorrowByIdDTO getMyBorrowByIdDTO) {
        if(!borrowDomainService.existsByIdBorrow(getMyBorrowByIdDTO.idBorrow())) return null;
        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.findBorrowByIdBorrowAndIdUser(getMyBorrowByIdDTO.idBorrow(),getMyBorrowByIdDTO.idUser()));
    }

    @Override
    public DataBorrowDTO getLatestBorrowByIdBook(GetLatestBorrowByIdBookDTO getLatestBorrowByIdBookDTO) {
        return borrowMapper.mapBorrowToDataBorrowDTO(borrowDomainService.findFirstByIdBookOrderByReturnDate(getLatestBorrowByIdBookDTO.idBook()));
    }

    @Override
    public List<DataBorrowDTO> getMyBorrows(GetMyBorrowsDTO getMyBorrowsDTO) {
        return borrowDomainService.findBorrowsByIdUser(getMyBorrowsDTO.idUser()).stream().map(borrowMapper::mapBorrowToDataBorrowDTO).collect(Collectors.toList());
    }

    @Override
    public List<DataBorrowDTO> recentReturnedBorrows(RecentReturnedBooksDTO recentReturnedBooksDTO) {
        return borrowDomainService.findAllByIdUserOrderByActualReturnDateDesc(recentReturnedBooksDTO.idUser()).stream().map(borrowMapper::mapBorrowToDataBorrowDTO).collect(Collectors.toList());
    }

    @Override
    public Object returnMyBorrowedBookById(Authentication authentication, UpdateMyBorrow updateMyBorrow) {
        if(!borrowDomainService.existsByIdBorrow(updateMyBorrow.idBorrow())) return "borrow does not exist";

        Borrow dbBorrow = borrowDomainService.findBorrowById(updateMyBorrow.idBorrow());

        if(!dbBorrow.getIdUser().equals(((Jwt) authentication.getPrincipal()).getSubject())) return "this is not your borrow";

        booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dbBorrow.getIdBook(), true)).getBody();

        dbBorrow.setActualReturnDate(new Date(System.currentTimeMillis()));

        borrowDomainService.saveBorrow(dbBorrow);
        return "Book returned";
    }
}
