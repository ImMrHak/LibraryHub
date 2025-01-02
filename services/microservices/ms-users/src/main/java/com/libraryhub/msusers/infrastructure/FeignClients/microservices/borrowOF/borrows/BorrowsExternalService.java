package com.libraryhub.msusers.infrastructure.FeignClients.microservices.borrowOF.borrows;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BORROW", url = "${application.config.ms-borrows}")
public interface BorrowsExternalService {
    @GetMapping("/myBorrows/count/{idUser}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultMyBorrowsCount")
    ResponseEntity<?> getMyBorrowsCount(@RequestHeader("idUser") String idUser);

    default ResponseEntity<?> getDefaultMyBorrowsCount(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch my borrows due to: " + exception.getMessage());
    }

    @GetMapping("/myReturnedBorrows/count/{idUser}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultMyReturnedBorrowsCount")
    ResponseEntity<?> getMyReturnedBorrowsCount(@RequestHeader("idUser") String idUser);

    default ResponseEntity<?> getDefaultMyReturnedBorrowsCount(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch my returned borrows count due to: " + exception.getMessage());
    }

    @GetMapping("/recentReturnedBorrows/{idUser}")
    @CircuitBreaker(name = "BorrowsServiceCB", fallbackMethod = "getDefaultRecentReturnedBorrows")
    ResponseEntity<?> recentReturnedBorrows(@RequestHeader("idUser") String idUser);

    default ResponseEntity<?> getDefaultRecentReturnedBorrows(Exception exception) {
        return ResponseEntity.ok("Fallback: Unable to fetch recent returned borrows due to: " + exception.getMessage());
    }
}
