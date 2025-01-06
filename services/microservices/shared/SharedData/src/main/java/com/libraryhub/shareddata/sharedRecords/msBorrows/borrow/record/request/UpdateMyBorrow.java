package com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request;

import jakarta.validation.constraints.NotNull;

public record UpdateMyBorrow(
        @NotNull
        Long idBorrow
) {
}
