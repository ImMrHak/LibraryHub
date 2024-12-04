package com.libraryhub.msborrows.infrastructure.booksOF.books.record.request;

import com.libraryhub.msborrows.infrastructure.booksOF.themes.record.request.DeleteThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DeleteThemeFromBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<DeleteThemeDTO> themes
) {
}
