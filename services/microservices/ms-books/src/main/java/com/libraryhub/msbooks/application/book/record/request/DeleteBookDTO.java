package com.libraryhub.msbooks.application.book.record.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DeleteBookDTO(
        @NotNull
        Long idBook
) {
}
