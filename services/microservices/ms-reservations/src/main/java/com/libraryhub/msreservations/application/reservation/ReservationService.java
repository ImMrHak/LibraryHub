package com.libraryhub.msreservations.application.reservation;

import com.libraryhub.msreservations.application.reservation.record.request.*;
import com.libraryhub.msreservations.application.reservation.record.response.DataReservationDTO;

import java.util.List;

public interface ReservationService {
    Object createReservation(CreateReservationDTO createReservationDTO);
    Object deleteReservation(DeleteReservationDTO deleteReservationDTO);
    List<DataReservationDTO> getReservations();
    List<DataReservationDTO> getMyReservations(GetMyReservationsDTO getMyReservationsDTO);
    List<DataReservationDTO> getActiveReservations();
    List<DataReservationDTO> getMyActiveReservations(GetMyActiveReservationsDTO getMyActiveReservationsDTO);
    DataReservationDTO getReservationById(GetReservationDTO getReservationDTO);
    DataReservationDTO getMyReservationById(GetMyReservationUserDTO getMyReservationUserDTO);
    DataReservationDTO getActiveReservationById(GetActiveReservationDTO getActiveReservationDTO);
    DataReservationDTO getMyActiveReservationById(GetMyActiveReservationDTO getMyActiveReservationDTO);
}
