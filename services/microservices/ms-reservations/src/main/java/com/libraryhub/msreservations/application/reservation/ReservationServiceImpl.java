package com.libraryhub.msreservations.application.reservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libraryhub.msreservations.application.reservation.mapper.ReservationMapper;
import com.libraryhub.msreservations.application.reservation.record.request.*;
import com.libraryhub.msreservations.application.reservation.record.response.DataReservationDTO;
import com.libraryhub.msreservations.domain.model.Reservation;
import com.libraryhub.msreservations.domain.service.ReservationDomainService;
import com.libraryhub.msreservations.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msreservations.infrastructure.booksOF.books.record.request.UpdateBookAvailabilityDTO;
import com.libraryhub.msreservations.infrastructure.booksOF.books.record.response.DataBookDTO;
import com.libraryhub.msreservations.infrastructure.booksOF.themes.ThemesExternalService;
import com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.BorrowsExternalService;
import com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.record.response.DataBorrowDTO;
import com.libraryhub.msreservations.infrastructure.usersOF.users.UsersExternalService;
import com.libraryhub.msreservations.infrastructure.usersOF.users.record.response.DataUserDTO;
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
    private final ThemesExternalService themesExternalService;
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
    public List<DataReservationDTO> getMyReservations(GetMyReservations getMyReservations) {
        return reservationDomainService.findAllByIdUser(getMyReservations.idUser()).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public List<DataReservationDTO> getActiveReservations() {
        return reservationDomainService.findAllByIsActive(true).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public List<DataReservationDTO> getMyActiveReservations(GetMyActiveReservations getMyActiveReservations) {
        return reservationDomainService.findAllByIdUserAndIsActive(getMyActiveReservations.idUser(), true).stream().map(reservationMapper::mapReservationToDataReservationDTO).collect(Collectors.toList());
    }

    @Override
    public DataReservationDTO getReservationById(GetReservation getReservation) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIsActive(getReservation.idReservation(), false));
    }

    @Override
    public DataReservationDTO getMyReservationById(GetMyReservationUser getMyReservationUser) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIdUser(getMyReservationUser.idReservation(), getMyReservationUser.idUser()));
    }

    @Override
    public DataReservationDTO getActiveReservationById(GetActiveReservation getActiveReservation) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIsActive(getActiveReservation.idReservation(), true));
    }

    @Override
    public DataReservationDTO getMyActiveReservationById(GetMyActiveReservation getMyActiveReservation) {
        return reservationMapper.mapReservationToDataReservationDTO(reservationDomainService.findReservationByIdReservationAndIdUserAndIsActive(getMyActiveReservation.idReservation(), getMyActiveReservation.idUser(), true));
    }
}
