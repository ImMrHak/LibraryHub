package com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteBorrowDTO(
        @NotNull
        Long idBorrow
) {
}
