package com.libraryhub.msbooks.application.book.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateBookDTO(
        @NotEmpty(message = "Field must not be empty")
        String title,
        @NotEmpty(message = "Field must not be empty")
        String author,
        @NotEmpty(message = "Field must not be empty")
        String isbn,
        @NotEmpty(message = "Field must not be empty")
        Integer publicationYear
) {
}
