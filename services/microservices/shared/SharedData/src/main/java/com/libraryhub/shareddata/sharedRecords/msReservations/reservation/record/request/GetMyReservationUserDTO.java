package com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyReservationUserDTO(
        @NotNull
        String idUser,
        @NotNull
        Long idReservation
) {
}
