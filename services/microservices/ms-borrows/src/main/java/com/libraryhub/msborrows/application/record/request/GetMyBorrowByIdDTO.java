package com.libraryhub.msborrows.application.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyBorrowByIdDTO(
        @NotNull
        Long idBorrow,
        @NotNull
        String idUser
) {
}
