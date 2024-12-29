package com.libraryhub.msusers.infrastructure.bookOF.books.record.request;

import jakarta.validation.constraints.NotNull;

public record RecoverBookDTO(
        @NotNull
        Long idBook
) {
}
