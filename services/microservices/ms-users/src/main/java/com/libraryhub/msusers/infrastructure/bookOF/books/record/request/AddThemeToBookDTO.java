package com.libraryhub.msusers.infrastructure.bookOF.books.record.request;

import com.libraryhub.msusers.infrastructure.bookOF.themes.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AddThemeToBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
