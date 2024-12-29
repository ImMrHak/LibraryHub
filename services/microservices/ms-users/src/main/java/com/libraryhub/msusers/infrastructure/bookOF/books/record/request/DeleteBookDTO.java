package com.libraryhub.msusers.infrastructure.bookOF.books.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteBookDTO(
        @NotNull
        Long idBook
) {
}
