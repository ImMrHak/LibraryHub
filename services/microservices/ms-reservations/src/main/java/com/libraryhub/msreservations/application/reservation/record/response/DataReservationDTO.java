package com.libraryhub.msreservations.application.reservation.record.response;

import java.util.Date;

public record DataReservationDTO(
        Long idReservation,
        Date reservationDate,
        Date pickUpDate,
        Integer queuePosition,
        Boolean isActive,
        String idUser,
        Long idBook
) {
}
