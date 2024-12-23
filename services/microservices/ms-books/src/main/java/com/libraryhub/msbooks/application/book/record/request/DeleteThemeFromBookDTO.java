package com.libraryhub.msbooks.application.book.record.request;

import com.libraryhub.msbooks.application.theme.record.request.DeleteThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DeleteThemeFromBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<DeleteThemeDTO> themes
) {
}
