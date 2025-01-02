package com.libraryhub.msbooks.application.book;

import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.*;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.response.DataBookDTO;

import java.util.List;

public interface BookService {
    Object createBook(CreateBookDTO createBookDTO);
    Object updateBook(UpdateBookDTO updateBookDTO);
    Object deleteBook(DeleteBookDTO deleteBookDTO);
    Object recoverBook(RecoverBookDTO recoverBookDTO);
    List<DataBookDTO> getBooks();
    List<DataBookDTO> getBooksByTitle(String title);
    Object getBooksWithPagination(Integer offset, Integer pageSize);
    List<DataBookDTO> getAvailableBooks();
    List<DataBookDTO> getUnavailableBooks();
    DataBookDTO getBookById(Long id);
    List<DataBookDTO> getDeletedBooks();
    DataBookDTO getDeletedBookById(Long id);
    Object addThemeToBook(AddThemeToBookDTO addThemeToBookDTO);
    Object deleteThemeFromBook(DeleteThemeFromBookDTO deleteThemeFromBookDTO);
    Object changeBookAvailability(UpdateBookAvailabilityDTO updateBookAvailabilityDTO);
    Object getBookByISBN(String isbn);
}
