package com.libraryhub.msreservations.infrastructure.booksOF.books.record.response;

import com.libraryhub.msreservations.infrastructure.booksOF.themes.record.response.DataThemeDTO;

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
