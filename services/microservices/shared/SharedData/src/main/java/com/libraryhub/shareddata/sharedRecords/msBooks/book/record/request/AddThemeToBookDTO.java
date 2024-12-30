package com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request;

import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.CreateThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AddThemeToBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<CreateThemeDTO> themes
) {
}
