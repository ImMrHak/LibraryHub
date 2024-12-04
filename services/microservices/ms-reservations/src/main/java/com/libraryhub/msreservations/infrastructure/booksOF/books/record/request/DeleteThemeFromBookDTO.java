package com.libraryhub.msreservations.infrastructure.booksOF.books.record.request;

import com.libraryhub.msreservations.infrastructure.booksOF.themes.record.request.DeleteThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DeleteThemeFromBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<DeleteThemeDTO> themes
) {
}
