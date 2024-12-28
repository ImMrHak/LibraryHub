package com.libraryhub.msusers.infrastructure.reservationOF.reservations.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteReservationDTO(
        @NotNull
        Long idReservation
) {
}
