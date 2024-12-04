package com.libraryhub.msreservations.infrastructure.booksOF.books.record.request;

import com.libraryhub.msreservations.infrastructure.booksOF.themes.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateBookDTO(
        @NotNull
        Long idBook,
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