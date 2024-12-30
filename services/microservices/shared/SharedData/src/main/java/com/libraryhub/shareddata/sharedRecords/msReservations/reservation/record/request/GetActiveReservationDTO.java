package com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request;

import jakarta.validation.constraints.NotNull;

public record GetActiveReservationDTO(
        @NotNull
        Long idReservation
) {
}
