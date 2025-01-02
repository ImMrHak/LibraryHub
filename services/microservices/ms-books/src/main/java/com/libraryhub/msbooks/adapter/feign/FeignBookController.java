package com.libraryhub.msbooks.adapter.feign;

import com.libraryhub.msbooks.application.book.BookService;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.UpdateBookAvailabilityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feign/v1/books")
@RequiredArgsConstructor
public class FeignBookController {
    private final BookService bookService;

    @GetMapping("/book/{isbn}")
    public ResponseEntity<?> getBookByISBN(@PathVariable("isbn") String isbn) {
        Object data = bookService.getBookByISBN(isbn);

        if (data instanceof String) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<?> getBookById(@PathVariable("idBook") Long idBook) {
        Object data = bookService.getBookById(idBook);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update/availability")
    public ResponseEntity<?> updateBookAvailability(@Valid @RequestBody UpdateBookAvailabilityDTO updateBookAvailabilityDTO) {
        Object data = bookService.changeBookAvailability(updateBookAvailabilityDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
