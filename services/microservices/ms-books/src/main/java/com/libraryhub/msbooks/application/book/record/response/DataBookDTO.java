package com.libraryhub.msbooks.application.book.record.response;

import com.libraryhub.msbooks.application.theme.response.DataThemeDTO;

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
