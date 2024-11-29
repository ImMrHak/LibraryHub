package com.libraryhub.msbooks.application.book.response;

import com.libraryhub.msbooks.application.theme.response.DataThemeDTO;
import com.libraryhub.msbooks.domain.theme.model.Theme;

import java.util.Set;

public record DataBookDTO(
        Long idBook,
        String title,
        String author,
        String isbn,
        Integer publicationYear,
        Boolean isAvailable,
        Set<DataThemeDTO> themes
) {
}
