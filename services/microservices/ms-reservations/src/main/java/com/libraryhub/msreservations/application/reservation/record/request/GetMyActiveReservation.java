package com.libraryhub.msreservations.application.reservation.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyActiveReservation(
        @NotNull
        String idUser,
        @NotNull
        Long idReservation
) {
}
