package com.libraryhub.msbooks.adapter.web;

import com.libraryhub.msbooks.application.book.BookService;
import com.libraryhub.msbooks.application.book.record.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<?> getAllBooks() {
        Object data = bookService.getBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks() {
        Object data = bookService.getAvailableBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<?> getUnavailableBooks() {
        Object data = bookService.getUnavailableBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<?> getBookById(@PathVariable("idBook") Long idBook) {
        Object data = bookService.getBookById(idBook);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/deletedBooks")
    public ResponseEntity<?> getAllDeletedBooks() {
        Object data = bookService.getDeletedBooks();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/deletedBooks/{idBook}")
    public ResponseEntity<?> getDeletedBookById(@PathVariable("idBook") Long idBook) {
        Object data = bookService.getDeletedBookById(idBook);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        Object data = bookService.createBook(createBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@Valid @RequestBody UpdateBookDTO updateBookDTO) {
        Object data = bookService.updateBook(updateBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update/availability")
    public ResponseEntity<?> updateBookAvailability(@Valid @RequestBody UpdateBookAvailabilityDTO updateBookAvailabilityDTO) {
        Object data = bookService.changeBookAvailability(updateBookAvailabilityDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update/addThemeToBook")
    public ResponseEntity<?> updateBookAddTheme(@Valid @RequestBody AddThemeToBookDTO addThemeToBookDTO) {
        Object data = bookService.addThemeToBook(addThemeToBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/recover")
    public ResponseEntity<?> recoverBook(@Valid @RequestBody RecoverBookDTO recoverBookDTO) {
        Object data = bookService.recoverBook(recoverBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBook(@Valid @RequestBody DeleteBookDTO deleteBookDTO) {
        Object data = bookService.deleteBook(deleteBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete/deleteThemeFromBook")
    public ResponseEntity<?> deleteThemeFromBook(@Valid @RequestBody DeleteThemeFromBookDTO deleteThemeFromBookDTO) {
        Object data = bookService.deleteThemeFromBook(deleteThemeFromBookDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
