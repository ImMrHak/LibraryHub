package com.libraryhub.msbooks.application.book.record.response;

import com.libraryhub.msbooks.application.theme.record.response.DataThemeDTO;

import java.util.Set;

public record DataBookDTO(
        Long idBook,
        String title,
        String author,
        String isbn,
        String imageUrl,
        Integer publicationYear,
        Boolean isAvailable,
        Set<DataThemeDTO> themes
) {
}
