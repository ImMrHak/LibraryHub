package com.libraryhub.msreservations.application.reservation.record.request;

import jakarta.validation.constraints.NotNull;

public record GetReservation(
        @NotNull
        Long idReservation
) {
}
