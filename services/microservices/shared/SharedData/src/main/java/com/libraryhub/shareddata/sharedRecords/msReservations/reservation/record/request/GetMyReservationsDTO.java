package com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyReservationsDTO(
        @NotNull
        String idUser
) {
}
