package com.libraryhub.shareddata.sharedRecords.msBooks.book.record.response;

import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.response.DataThemeDTO;

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
