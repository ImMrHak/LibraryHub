package com.libraryhub.msreservations.infrastructure.FeignClients.microservices.bookOF.books;

import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.*;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BOOKS", url = "${application.config.ms-books}")
public interface BooksExternalService {
    @GetMapping("/{idBook}")
    ResponseEntity<?> getBookById(@PathVariable("idBook") Long idBook);

    @PutMapping("/update/availability")
    ResponseEntity<?> updateBookAvailability(@Valid @RequestBody UpdateBookAvailabilityDTO updateBookAvailabilityDTO);
}
