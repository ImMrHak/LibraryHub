package com.libraryhub.msbooks.application.book;

import com.libraryhub.msbooks.application.book.request.*;
import com.libraryhub.msbooks.application.book.response.DataBookDTO;

import java.util.List;

public interface BookService {
    Object createBook(CreateBookDTO createBookDTO);
    Object updateBook(UpdateBookDTO updateBookDTO);
    Object deleteBook(DeleteBookDTO deleteBookDTO);
    Object recoverBook(RecoverBookDTO recoverBookDTO);
    List<DataBookDTO> getBooks();
    DataBookDTO getBookById(Long id);
    List<DataBookDTO> getDeletedBooks();
    DataBookDTO getDeletedBookById(Long id);
    Object addThemeToBook(AddThemeToBookDTO addThemeToBookDTO);
    Object deleteThemeFromBook(DeleteThemeFromBookDTO deleteThemeFromBookDTO);
}