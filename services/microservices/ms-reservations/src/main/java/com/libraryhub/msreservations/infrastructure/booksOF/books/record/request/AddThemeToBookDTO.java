package com.libraryhub.msreservations.infrastructure.booksOF.books.record.request;

import com.libraryhub.msreservations.infrastructure.booksOF.themes.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AddThemeToBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
