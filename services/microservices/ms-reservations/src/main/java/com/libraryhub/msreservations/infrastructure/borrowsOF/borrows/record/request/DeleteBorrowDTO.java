package com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteBorrowDTO(
        @NotNull
        Long idBorrow
) {
}
