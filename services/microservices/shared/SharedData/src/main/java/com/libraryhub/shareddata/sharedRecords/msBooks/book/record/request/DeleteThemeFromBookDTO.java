package com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request;

import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.DeleteThemeDTO;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record DeleteThemeFromBookDTO(
        @NotNull
        Long idBook,
        @NotNull
        Set<DeleteThemeDTO> themes
) {
}
