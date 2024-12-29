package com.libraryhub.msusers.infrastructure.bookOF.books.record.request;

import com.libraryhub.msusers.infrastructure.bookOF.themes.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateBookDTO(
        @NotEmpty(message = "Field must not be empty")
        String title,
        @NotEmpty(message = "Field must not be empty")
        String author,
        @NotEmpty(message = "Field must not be empty")
        String isbn,
        @NotNull
        Integer publicationYear,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
