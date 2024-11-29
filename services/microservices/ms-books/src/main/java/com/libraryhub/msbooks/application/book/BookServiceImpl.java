package com.libraryhub.msbooks.application.book;

import com.libraryhub.msbooks.application.book.mapper.BookMapper;
import com.libraryhub.msbooks.application.book.request.CreateBookDTO;
import com.libraryhub.msbooks.application.book.request.DeleteBookDTO;
import com.libraryhub.msbooks.application.book.request.RecoverBookDTO;
import com.libraryhub.msbooks.application.book.request.UpdateBookDTO;
import com.libraryhub.msbooks.application.book.response.DataBookDTO;
import com.libraryhub.msbooks.domain.book.model.Book;
import com.libraryhub.msbooks.domain.book.service.BookDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDomainService bookDomainService;
    private final BookMapper bookMapper;

    @Override
    public Object createBook(CreateBookDTO createBookDTO) {
        if(bookDomainService.existsBookByTitle(createBookDTO.title())) return "Book already exists";

        return bookMapper.mapBookToDataBookDTO(bookDomainService.saveBook(bookMapper.mapCreateDTOToBook(createBookDTO)));
    }

    @Override
    public Object updateBook(UpdateBookDTO updateBookDTO) {
        if(!bookDomainService.existsByIdBook(updateBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(updateBookDTO.idBook());

        if(dbBook.getIsDeleted()) return "Book does not exist because it's deleted";

        dbBook.setTitle(updateBookDTO.title());
        dbBook.setAuthor(updateBookDTO.author());
        dbBook.setIsbn(updateBookDTO.isbn());
        dbBook.setPublicationYear(updateBookDTO.publicationYear());

        return bookMapper.mapBookToDataBookDTO(bookDomainService.saveBook(dbBook));
    }

    @Override
    public Object deleteBook(DeleteBookDTO deleteBookDTO) {
        if(!bookDomainService.existsByIdBook(deleteBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(deleteBookDTO.idBook());

        if(dbBook.getIsDeleted()) return "Book is already deleted";

        dbBook.setIsDeleted(true);

        bookDomainService.saveBook(dbBook);
        return true;
    }

    @Override
    public Object recoverBook(RecoverBookDTO recoverBookDTO) {
        if(!bookDomainService.existsByIdBook(recoverBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(recoverBookDTO.idBook());

        if(!dbBook.getIsDeleted()) return "Book is already not deleted";

        dbBook.setIsDeleted(false);

        bookDomainService.saveBook(dbBook);
        return true;
    }

    @Override
    public List<DataBookDTO> getBooks() {
        return bookDomainService.findAll().stream().map(bookMapper::mapBookToDataBookDTO).collect(Collectors.toList());
    }

    @Override
    public DataBookDTO getBookById(Long id) {
        return bookMapper.mapBookToDataBookDTO(bookDomainService.findBookById(id));
    }
}
