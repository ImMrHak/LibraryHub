package com.libraryhub.msbooks.adapter.web;

import com.libraryhub.msbooks.application.book.BookService;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping() @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getBooks() {
        Object data = bookService.getBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBookByTitle(@RequestParam(name = "title") String title) {
        Object data = bookService.getBooksByTitle(title);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{offset}/{pageSize}")
    @CircuitBreaker(name = "BooksServiceCB", fallbackMethod = "getDefaultBooks")
    public ResponseEntity<?> getBooks(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize) {
        Object data = bookService.getBooksWithPagination(offset, pageSize);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    public ResponseEntity<?> getDefaultBooks(int offset, int pageSize, Throwable throwable) {
        // Define the fallback response
        String errorMessage = "Books service is temporarily unavailable. Please try again later.";
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorMessage);
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<?> getBookByISBN(@PathVariable("isbn") String isbn) {
        Object data = bookService.getBookByISBN(isbn);

        if (data instanceof String) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/available") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAvailableBooks() {
        Object data = bookService.getAvailableBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/unavailable") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUnavailableBooks() {
        Object data = bookService.getUnavailableBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idBook}") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getBookById(@PathVariable("idBook") Long idBook) {
        Object data = bookService.getBookById(idBook);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/deletedBooks") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllDeletedBooks() {
        Object data = bookService.getDeletedBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/deletedBooks/{idBook}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getDeletedBookById(@PathVariable("idBook") Long idBook) {
        Object data = bookService.getDeletedBookById(idBook);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/create") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        Object data = bookService.createBook(createBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateBook(@Valid @RequestBody UpdateBookDTO updateBookDTO) {
        Object data = bookService.updateBook(updateBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    // MAYBE ERROR CONCERNING UPDATING THE BOOK AVAILABILITY USING ANOTHER MICROSERVICE (BORROWS/RESERVATIONS)
    @PutMapping("/update/availability")
    public ResponseEntity<?> updateBookAvailability(@Valid @RequestBody UpdateBookAvailabilityDTO updateBookAvailabilityDTO) {
        Object data = bookService.changeBookAvailability(updateBookAvailabilityDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update/addThemeToBook") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateBookAddTheme(@Valid @RequestBody AddThemeToBookDTO addThemeToBookDTO) {
        Object data = bookService.addThemeToBook(addThemeToBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/recover") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> recoverBook(@Valid @RequestBody RecoverBookDTO recoverBookDTO) {
        Object data = bookService.recoverBook(recoverBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteBook(@Valid @RequestBody DeleteBookDTO deleteBookDTO) {
        Object data = bookService.deleteBook(deleteBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete/deleteThemeFromBook") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteThemeFromBook(@Valid @RequestBody DeleteThemeFromBookDTO deleteThemeFromBookDTO) {
        Object data = bookService.deleteThemeFromBook(deleteThemeFromBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
