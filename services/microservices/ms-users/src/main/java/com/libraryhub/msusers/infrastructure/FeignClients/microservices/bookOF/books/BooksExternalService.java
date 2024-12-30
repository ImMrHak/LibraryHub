package com.libraryhub.msusers.infrastructure.FeignClients.microservices.bookOF.books;

import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.*;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BOOKS", url = "${application.config.ms-books}", configuration = FeignClientsConfiguration.class)
public interface BooksExternalService {
    @GetMapping("")
    ResponseEntity<?> getAllBooks();

    @GetMapping("/available")
    ResponseEntity<?> getAvailableBooks();

    @GetMapping("/unavailable")
    ResponseEntity<?> getUnavailableBooks();

    @GetMapping("/{idBook}")
    ResponseEntity<?> getBookById(@PathVariable("idBook") Long idBook);

    @GetMapping("/deletedBooks")
    ResponseEntity<?> getAllDeletedBooks();

    @GetMapping("/deletedBooks/{idBook}")
    ResponseEntity<?> getDeletedBookById(@PathVariable("idBook") Long idBook);

    @PostMapping("/create")
    ResponseEntity<?> createBook(@Valid @RequestBody CreateBookDTO createBookDTO);

    @PutMapping("/update")
    ResponseEntity<?> updateBook(@Valid @RequestBody UpdateBookDTO updateBookDTO);

    @PutMapping("/update/availability")
    ResponseEntity<?> updateBookAvailability(@Valid @RequestBody UpdateBookAvailabilityDTO updateBookAvailabilityDTO);

    @PutMapping("/update/addThemeToBook")
    ResponseEntity<?> updateBookAddTheme(@Valid @RequestBody AddThemeToBookDTO addThemeToBookDTO);

    @PutMapping("/recover")
    ResponseEntity<?> recoverBook(@Valid @RequestBody RecoverBookDTO recoverBookDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteBook(@Valid @RequestBody DeleteBookDTO deleteBookDTO);

    @DeleteMapping("/delete/deleteThemeFromBook")
    ResponseEntity<?> deleteThemeFromBook(@Valid @RequestBody DeleteThemeFromBookDTO deleteThemeFromBookDTO);
}
