package com.libraryhub.msreservations.application.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryhub.msreservations.application.reservation.mapper.ReservationMapper;
import com.libraryhub.msreservations.domain.reservation.model.Reservation;
import com.libraryhub.msreservations.domain.reservation.service.ReservationDomainService;
import com.libraryhub.msreservations.infrastructure.FeignClients.microservices.bookOF.books.BooksExternalService;
import com.libraryhub.msreservations.infrastructure.FeignClients.microservices.borrowOF.borrows.BorrowsExternalService;
import com.libraryhub.msreservations.infrastructure.FeignClients.microservices.usersOF.users.UsersExternalService;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.UpdateBookAvailabilityDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.response.DataBookDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.response.DataBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request.*;
import com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.response.DataReservationDTO;
import com.libraryhub.shareddata.sharedRecords.msUsers.user.record.response.DataUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDomainService reservationDomainService;
    private final ReservationMapper reservationMapper;
    private final BooksExternalService booksExternalService;
    private final UsersExternalService usersExternalService;
    private final BorrowsExternalService borrowsExternalService;

    @Override
    public Object createReservation(CreateReservationDTO createReservationDTO) {
        Object responseBookBody = booksExternalService.getBookById(createReservationDTO.idBook()).getBody();
        DataBookDTO dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);

        Object responseUserBody = usersExternalService.getUserById(createReservationDTO.idUser()).getBody();
        DataUserDTO dataUserDTO = new ObjectMapper().convertValue(responseUserBody, DataUserDTO.class);

        Object responseBorrowBody = borrowsExternalService.getLatestBorrowByIdBook(createReservationDTO.idBook()).getBody();
        DataBorrowDTO dataBorrowDTO = new ObjectMapper().convertValue(responseBorrowBody, DataBorrowDTO.class);

        if(dataBookDTO == null) return "The book does not exist";
        if(dataUserDTO == null) return "The user does not exist";
        if(Objects.equals(dataBorrowDTO.actualReturnDate(), new Date(System.currentTimeMillis()))){
            responseBookBody = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dataBookDTO.idBook(), true)).getBody();
            dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);

            if (responseBookBody == null || !dataBookDTO.isAvailable()) {
                return "Error updating book availability";
            }

            return "You should borrow this book instead of reserving it";
        }
        if(dataBookDTO.isAvailable()) return "This Book is available which mean you can borrow it";

        Reservation reservation = reservationMapper.mapCreateReservationDTOToReservation(createReservationDTO);

        responseBookBody = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dataBookDTO.idBook(), false)).getBody();
        dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);

        if (responseBookBody == null || dataBookDTO.isAvailable()) {
            return "Error updating book availability";
        }


        reservation.setReservationDate(new Date(System.currentTimeMillis()));
        reservation.setPickUpDate(dataBorrowDTO.returnDate());
        reservation.setIsActive(reservation.getReservationDate().equals(dataBorrowDTO.actualReturnDate()));

        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.saveReservation(reservation));
    }

    @Override
    public Object deleteReservation(DeleteReservationDTO deleteReservationDTO) {
        if(!reservationDomainService.existsByIdReservation(deleteReservationDTO.idReservation())) return "the reservation not exist";

        Reservation dbReservation = reservationDomainService.findReservationById(deleteReservationDTO.idReservation());

        Object responseBorrowBody = borrowsExternalService.getLatestBorrowByIdBook(dbReservation.getIdBook()).getBody();
        DataBorrowDTO dataBorrowDTO = new ObjectMapper().convertValue(responseBorrowBody, DataBorrowDTO.class);

        if(Objects.equals(dataBorrowDTO.actualReturnDate(), new Date(System.currentTimeMillis()))) {
            Object responseBookBody = booksExternalService.updateBookAvailability(new UpdateBookAvailabilityDTO(dbReservation.getIdBook(), true)).getBody();
            DataBookDTO dataBookDTO = new ObjectMapper().convertValue(responseBookBody, DataBookDTO.class);

            if (responseBookBody == null || !dataBookDTO.isAvailable()) {
                return "Error updating book availability";
            }

            return "You should borrow this book instead of reserving it";
        }

        return reservationDomainService.deleteReservationById(deleteReservationDTO.idReservation());
    }

    @Override
    public List<DataReservationDTO> getReservations() {
        return reservationDomainService.findAllByIsActive(false).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public List<DataReservationDTO> getMyReservations(GetMyReservationsDTO getMyReservationsDTO) {
        return reservationDomainService.findAllByIdUser(getMyReservationsDTO.idUser()).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public Integer getMyReservationsCount(GetMyReservationsDTO getMyReservationsDTO) {
        return reservationDomainService.countAllByIdUser(getMyReservationsDTO.idUser(),true);
    }

    @Override
    public List<DataReservationDTO> getActiveReservations() {
        return reservationDomainService.findAllByIsActive(true).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public List<DataReservationDTO> getMyActiveReservations(GetMyActiveReservationsDTO getMyActiveReservationsDTO) {
        return reservationDomainService.findAllByIdUserAndIsActive(getMyActiveReservationsDTO.idUser(), true).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public DataReservationDTO getReservationById(GetReservationDTO getReservationDTO) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIsActive(getReservationDTO.idReservation(), false));
    }

    @Override
    public DataReservationDTO getMyReservationById(GetMyReservationUserDTO getMyReservationUserDTO) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIdUser(getMyReservationUserDTO.idReservation(), getMyReservationUserDTO.idUser()));
    }

    @Override
    public DataReservationDTO getActiveReservationById(GetActiveReservationDTO getActiveReservationDTO) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIsActive(getActiveReservationDTO.idReservation(), true));
    }

    @Override
    public DataReservationDTO getMyActiveReservationById(GetMyActiveReservationDTO getMyActiveReservationDTO) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIdUserAndIsActive(getMyActiveReservationDTO.idReservation(), getMyActiveReservationDTO.idUser(), true));
    }
}
