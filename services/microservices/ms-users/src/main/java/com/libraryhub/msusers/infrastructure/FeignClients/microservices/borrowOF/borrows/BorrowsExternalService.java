package com.libraryhub.msusers.infrastructure.FeignClients.microservices.borrowOF.borrows;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BORROW", url = "${application.config.ms-borrows}")
public interface BorrowsExternalService {
    @GetMapping("/myBorrows/count/{idUser}")
    ResponseEntity<?> getMyBorrowsCount(@RequestHeader("idUser") String idUser);

    @GetMapping("/myReturnedBorrows/count/{idUser}")
    ResponseEntity<?> getMyReturnedBorrowsCount(@RequestHeader("idUser") String idUser);

    @GetMapping("/recentReturnedBorrows/{idUser}")
    ResponseEntity<?> recentReturnedBorrows(@RequestHeader("idUser") String idUser);
}
