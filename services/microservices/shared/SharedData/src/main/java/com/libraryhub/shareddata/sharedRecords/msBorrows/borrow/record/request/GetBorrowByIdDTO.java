package com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request;

import jakarta.validation.constraints.NotNull;

public record GetBorrowByIdDTO(
        @NotNull
        Long idBorrow

) {
}
