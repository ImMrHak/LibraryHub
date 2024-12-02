package com.libraryhub.msreservations.application.reservation;

import com.libraryhub.msreservations.application.reservation.record.request.CreateReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.request.DeleteReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.request.UpdateReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.response.DataReservationDTO;

import java.util.List;

public interface ReservationService {
    Object createReservation(CreateReservationDTO createReservationDTO);
    Object updateReservation(UpdateReservationDTO updateReservationDTO);
    Object deleteReservation(DeleteReservationDTO deleteReservationDTO);
    List<DataReservationDTO> getReservations();
    DataReservationDTO getReservationById(Long id);
}
