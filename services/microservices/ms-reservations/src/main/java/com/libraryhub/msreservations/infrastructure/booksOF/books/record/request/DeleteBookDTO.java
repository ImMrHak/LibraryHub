package com.libraryhub.msreservations.infrastructure.booksOF.books.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteBookDTO(
        @NotNull
        Long idBook
) {
}
