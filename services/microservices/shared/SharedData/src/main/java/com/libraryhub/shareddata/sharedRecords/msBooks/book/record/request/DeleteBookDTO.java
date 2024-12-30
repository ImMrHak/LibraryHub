package com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteBookDTO(
        @NotNull
        Long idBook
) {
}
