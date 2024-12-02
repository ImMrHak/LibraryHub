package com.libraryhub.msreservations.application.reservation.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateReservationDTO(
        @NotNull
        Long idReservation,
        @NotNull
        Date reservationDate,
        @NotNull
        Date pickUpDate,
        @NotNull
        Long idBook
) {
}
