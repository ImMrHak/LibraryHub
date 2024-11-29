package com.libraryhub.msbooks.application.book.response;

public record DataBookDTO(
        Long idBook,
        String title,
        String author,
        String isbn,
        Integer publicationYear,
        Boolean isAvailable
) {
}
