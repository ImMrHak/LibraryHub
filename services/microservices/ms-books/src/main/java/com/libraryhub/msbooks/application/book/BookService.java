package com.libraryhub.msbooks.application.book;

import com.libraryhub.msbooks.application.book.record.request.*;
import com.libraryhub.msbooks.application.book.record.response.DataBookDTO;

import java.util.List;

public interface BookService {
    Object createBook(CreateBookDTO createBookDTO);
    Object updateBook(UpdateBookDTO updateBookDTO);
    Object deleteBook(DeleteBookDTO deleteBookDTO);
    Object recoverBook(RecoverBookDTO recoverBookDTO);
    List<DataBookDTO> getBooks();
    List<DataBookDTO> getAvailableBooks();
    List<DataBookDTO> getUnavailableBooks();
    DataBookDTO getBookById(Long id);
    List<DataBookDTO> getDeletedBooks();
    DataBookDTO getDeletedBookById(Long id);
    Object addThemeToBook(AddThemeToBookDTO addThemeToBookDTO);
    Object deleteThemeFromBook(DeleteThemeFromBookDTO deleteThemeFromBookDTO);
    Object changeBookAvailability(UpdateBookAvailabilityDTO updateBookAvailabilityDTO);
}
