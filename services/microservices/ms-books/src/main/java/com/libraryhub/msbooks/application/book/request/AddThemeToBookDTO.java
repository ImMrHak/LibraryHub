package com.libraryhub.msbooks.application.book.request;

import com.libraryhub.msbooks.application.theme.request.CreateThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AddThemeToBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
