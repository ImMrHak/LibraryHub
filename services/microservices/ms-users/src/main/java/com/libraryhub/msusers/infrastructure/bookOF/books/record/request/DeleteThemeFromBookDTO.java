package com.libraryhub.msusers.infrastructure.bookOF.books.record.request;

import com.libraryhub.msusers.infrastructure.bookOF.themes.record.request.DeleteThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DeleteThemeFromBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<DeleteThemeDTO> themes
) {
}
