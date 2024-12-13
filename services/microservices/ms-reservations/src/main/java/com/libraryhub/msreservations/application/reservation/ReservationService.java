package com.libraryhub.msreservations.application.reservation;

import com.libraryhub.msreservations.application.reservation.record.request.*;
import com.libraryhub.msreservations.application.reservation.record.response.DataReservationDTO;

import java.util.List;

public interface ReservationService {
    Object createReservation(CreateReservationDTO createReservationDTO);
    Object deleteReservation(DeleteReservationDTO deleteReservationDTO);
    List<DataReservationDTO> getReservations();
    List<DataReservationDTO> getMyReservations(GetMyReservations getMyReservations);
    List<DataReservationDTO> getActiveReservations();
    List<DataReservationDTO> getMyActiveReservations(GetMyActiveReservations getMyActiveReservations);
    DataReservationDTO getReservationById(GetReservation getReservation);
    DataReservationDTO getMyReservationById(GetMyReservationUser getMyReservationUser);
    DataReservationDTO getActiveReservationById(GetActiveReservation getActiveReservation);
    DataReservationDTO getMyActiveReservationById(GetMyActiveReservation getMyActiveReservation);
}
