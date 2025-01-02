package com.libraryhub.msreservations.infrastructure.FeignClients.microservices.borrowOF.borrows;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BORROW", url = "${application.config.ms-borrows}")
public interface BorrowsExternalService {
    @GetMapping("/latestBorrowedBook/{idBook}")
    ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook);
}
