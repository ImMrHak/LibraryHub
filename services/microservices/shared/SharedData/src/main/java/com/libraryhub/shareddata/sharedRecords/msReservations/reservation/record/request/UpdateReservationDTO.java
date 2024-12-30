package com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateReservationDTO(
        @NotNull
        Long idReservation,
        @NotNull
        Date reservationDate,
        Date pickUpDate,
        @NotNull
        Long idBook
) {
}
