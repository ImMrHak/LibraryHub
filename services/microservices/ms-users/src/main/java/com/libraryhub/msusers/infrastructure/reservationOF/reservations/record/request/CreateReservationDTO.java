package com.libraryhub.msusers.infrastructure.reservationOF.reservations.record.request;

import jakarta.validation.constraints.NotNull;

public record CreateReservationDTO(
        @NotNull
        String idUser,
        @NotNull
        Long idBook
) {
        public CreateReservationDTO withIdUser(String newIdUser) {
                return new CreateReservationDTO(newIdUser, this.idBook);
        }
}
