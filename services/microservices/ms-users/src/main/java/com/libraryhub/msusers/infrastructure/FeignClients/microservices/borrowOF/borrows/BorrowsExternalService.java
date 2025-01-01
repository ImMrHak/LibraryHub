package com.libraryhub.msusers.infrastructure.FeignClients.microservices.borrowOF.borrows;

import com.libraryhub.msusers.infrastructure.FeignClients.Config.FeignClientConfig;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.CreateBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.DeleteBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.UpdateBorrowDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BORROW", url = "${application.config.ms-borrows}", configuration = FeignClientConfig.class)
public interface BorrowsExternalService {

    @GetMapping
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultBorrows")
    ResponseEntity<?> getBorrows();

    default ResponseEntity<?> getDefaultBorrows(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch borrows due to: " + exception.getMessage());
    }

    @GetMapping("/borrowedBook/{isbn}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultBorrowedBook")
    ResponseEntity<?> getBorrowedBook(@PathVariable("isbn") String isbn);

    default ResponseEntity<?> getDefaultBorrowedBook(String isbn,Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch borrowed book for ISBN " + isbn + " due to: " + exception.getMessage());
    }

    @GetMapping("/myBorrows/count")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultMyBorrowsCount")
    ResponseEntity<?> getMyBorrowsCount();

    default ResponseEntity<?> getDefaultMyBorrowsCount(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch my borrows due to: " + exception.getMessage());
    }

    @GetMapping("/myBorrows")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultMyBorrows")
    ResponseEntity<?> getMyBorrows();

    default ResponseEntity<?> getDefaultMyBorrows(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch my borrows due to: " + exception.getMessage());
    }

    @GetMapping("/myBorrow/{idBorrow}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultMyBorrow")
    ResponseEntity<?> getMyBorrow(@PathVariable("idBorrow") Long idBorrow);

    default ResponseEntity<?> getDefaultMyBorrow(Long idBorrow,Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch my borrow with ID " + idBorrow + " due to: " + exception.getMessage());
    }

    @GetMapping("/myReturnedBorrows/count")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultMyReturnedBorrowsCount")
    ResponseEntity<?> getMyReturnedBorrowsCount();

    default ResponseEntity<?> getDefaultMyReturnedBorrowsCount(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch my returned borrows count due to: " + exception.getMessage());
    }

    @GetMapping("/latestBorrowedBook/{idBook}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultLatestBorrowByIdBook")
    ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook);

    default ResponseEntity<?> getDefaultLatestBorrowByIdBook(Long idBook,Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch latest borrow for book ID " + idBook + " due to: " + exception.getMessage());
    }

    @GetMapping("/{idBorrow}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultBorrowById")
    ResponseEntity<?> getBorrowById(@PathVariable("idBorrow") Long idBorrow);

    default ResponseEntity<?> getDefaultBorrowById(Long idBorrow,Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch borrow with ID " + idBorrow + " due to: " + exception.getMessage());
    }

    @GetMapping("/recentReturnedBorrows")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultRecentReturnedBorrows")
    ResponseEntity<?> recentReturnedBorrows();

    default ResponseEntity<?> getDefaultRecentReturnedBorrows(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch recent returned borrows due to: " + exception.getMessage());
    }


    @PostMapping("/create")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultCreateBorrow")
    ResponseEntity<?> createBorrow(@RequestBody CreateBorrowDTO createBorrowDTO);

    // Fallback method
    default ResponseEntity<?> getDefaultCreateBorrow(CreateBorrowDTO createBorrowDTO, Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to create borrow for " + createBorrowDTO + " due to: " + exception.getMessage());
    }
    @PutMapping("/update")
    ResponseEntity<?> updateBorrow(@RequestBody UpdateBorrowDTO updateBorrowDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteBorrow(@RequestBody DeleteBorrowDTO deleteBorrowDTO);
}
