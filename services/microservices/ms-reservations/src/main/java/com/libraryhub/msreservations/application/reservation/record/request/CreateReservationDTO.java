package com.libraryhub.msreservations.application.reservation.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateReservationDTO(
        @NotNull
        String idUser,
        @NotNull
        Long idBook
) {
}
