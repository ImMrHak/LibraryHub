package com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyActiveReservationDTO(
        @NotNull
        String idUser,
        @NotNull
        Long idReservation
) {
}
