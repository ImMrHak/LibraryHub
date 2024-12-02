package com.libraryhub.msbooks.application.book.record.request;

import com.libraryhub.msbooks.application.theme.request.DeleteThemeDTO;
import com.libraryhub.msbooks.application.theme.response.DataThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DeleteThemeFromBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<DeleteThemeDTO> themes
) {
}
