package com.libraryhub.msbooks.application.book.record.request;

import jakarta.validation.constraints.NotNull;

public record UpdateBookAvailabilityDTO(
        @NotNull
        Long idBook,
        @NotNull
        Boolean isAvailable
) {
}
