package com.libraryhub.msborrows.infrastructure.booksOF.books.record.request;

import jakarta.validation.constraints.NotNull;

public record UpdateBookAvailabilityDTO(
        @NotNull
        Long idBook,

        @NotNull
        Boolean isAvailable
) {
}
