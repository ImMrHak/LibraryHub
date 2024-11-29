package com.libraryhub.msbooks.application.book.request;

import jakarta.validation.constraints.NotEmpty;

public record DeleteBookDTO(
        @NotEmpty(message = "Field must not be empty")
        Long idBook
) {
}
