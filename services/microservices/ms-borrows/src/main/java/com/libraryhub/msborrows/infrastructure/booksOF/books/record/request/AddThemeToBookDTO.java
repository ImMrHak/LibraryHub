package com.libraryhub.msborrows.infrastructure.booksOF.books.record.request;

import com.libraryhub.msborrows.infrastructure.booksOF.themes.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AddThemeToBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
