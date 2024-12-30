package com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request;

import jakarta.validation.constraints.NotNull;

public record UpdateBookAvailabilityDTO(
        @NotNull
        Long idBook,
        @NotNull
        Boolean isAvailable
) {
}
