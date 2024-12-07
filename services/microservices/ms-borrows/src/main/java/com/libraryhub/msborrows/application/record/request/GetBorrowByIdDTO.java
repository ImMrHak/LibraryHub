package com.libraryhub.msborrows.application.record.request;

import jakarta.validation.constraints.NotNull;

public record GetBorrowByIdDTO(
        @NotNull
        Long idBorrow,
        @NotNull
        String IdUser
) {
}
