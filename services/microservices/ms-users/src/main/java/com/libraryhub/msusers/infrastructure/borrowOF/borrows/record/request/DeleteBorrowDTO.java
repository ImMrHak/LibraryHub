package com.libraryhub.msusers.infrastructure.borrowOF.borrows.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteBorrowDTO(
        @NotNull
        Long idBorrow
) {
}
