package com.libraryhub.msborrows.infrastructure.booksOF.books;

import com.libraryhub.msborrows.infrastructure.booksOF.books.record.request.CreateBookDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.books.record.request.DeleteBookDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.books.record.request.UpdateBookAvailabilityDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.books.record.request.UpdateBookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BOOKS", url = "${application.config.ms-books}")
public interface BooksExternalService {
    @GetMapping
    ResponseEntity<?> getAllBooks();

    @GetMapping("/{idBook}")
    ResponseEntity<?> getBookById(@PathVariable("idBook") Long idBook);

    @PostMapping("/create")
    ResponseEntity<?> createBook(@RequestBody CreateBookDTO createBookDTO);

    @PutMapping("/update")
    ResponseEntity<?> updateBook(@RequestBody UpdateBookDTO updateBookDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteBook(@RequestBody DeleteBookDTO deleteBookDTO);

    @PutMapping("/update/availability")
    ResponseEntity<?> updateBookAvailability(@RequestBody UpdateBookAvailabilityDTO updateBookAvailabilityDTO);
}
