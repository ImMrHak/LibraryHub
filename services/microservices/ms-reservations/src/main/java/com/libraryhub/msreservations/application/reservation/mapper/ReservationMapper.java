package com.libraryhub.msreservations.application.reservation.mapper;

import com.libraryhub.msreservations.application.reservation.record.request.CreateReservationDTO;
import com.libraryhub.msreservations.application.reservation.record.response.DataReservationDTO;
import com.libraryhub.msreservations.domain.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation mapCreateReservationDTOToReservation(CreateReservationDTO createReservationDTO);
    DataReservationDTO mapReservationToDataReservationDTO(Reservation reservation);
}
