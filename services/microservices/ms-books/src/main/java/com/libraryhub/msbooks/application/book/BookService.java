package com.libraryhub.msbooks.application.book;

import com.libraryhub.msbooks.application.book.request.CreateBookDTO;
import com.libraryhub.msbooks.application.book.request.DeleteBookDTO;
import com.libraryhub.msbooks.application.book.request.RecoverBookDTO;
import com.libraryhub.msbooks.application.book.request.UpdateBookDTO;
import com.libraryhub.msbooks.application.book.response.DataBookDTO;

import java.util.List;

public interface BookService {
    Object createBook(CreateBookDTO createBookDTO);
    Object updateBook(UpdateBookDTO updateBookDTO);
    Object deleteBook(DeleteBookDTO deleteBookDTO);
    Object recoverBook(RecoverBookDTO recoverBookDTO);
    List<DataBookDTO> getBooks();
    DataBookDTO getBookById(Long id);
}
