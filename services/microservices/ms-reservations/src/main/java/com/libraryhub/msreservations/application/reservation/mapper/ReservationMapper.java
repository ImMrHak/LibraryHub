package com.libraryhub.msreservations.application.reservation.mapper;

import com.libraryhub.msreservations.domain.model.Reservation;
import com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request.CreateReservationDTO;
import com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.response.DataReservationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation mapCreateReservationDTOToReservation(CreateReservationDTO createReservationDTO);
    DataReservationDTO mapReservationToDataReservationDTO(Reservation reservation);
}
