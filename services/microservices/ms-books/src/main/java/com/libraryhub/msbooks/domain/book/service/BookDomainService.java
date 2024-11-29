package com.libraryhub.msbooks.domain.book.service;

import com.libraryhub.msbooks.domain.book.model.Book;
import com.libraryhub.msbooks.domain.book.repository.BookDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDomainService {
    private final BookDomainRepository bookDomainRepository;

    public List<Book> findAll(){
        return bookDomainRepository.findAll();
    }

    public Book findBookById(Long id){
        return bookDomainRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book){
        return bookDomainRepository.save(book);
    }

    public Boolean deleteBookById(Long id){
        Book dbBook = findBookById(id);

        if(dbBook == null) return false;

        bookDomainRepository.delete(dbBook);
        return true;
    }

    public Boolean deleteBook(Book book){
        Book dbBook = findBookById(book.getIdBook());

        if(dbBook == null) return false;

        dbBook.setIsDeleted(true);

        bookDomainRepository.save(dbBook);
        return true;
    }

    public Boolean existsBookByTitle(String title){
        return bookDomainRepository.existsBookByTitle(title);
    }

    public Boolean existsByIdBook(Long idBook){
        return bookDomainRepository.existsByIdBook(idBook);
    }
}
