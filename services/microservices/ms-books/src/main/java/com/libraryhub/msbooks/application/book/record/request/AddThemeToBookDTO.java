package com.libraryhub.msbooks.application.book.record.request;

import com.libraryhub.msbooks.application.theme.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AddThemeToBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
