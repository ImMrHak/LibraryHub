package com.libraryhub.msreservations.infrastructure.borrowsOF.borrows;

import com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.record.request.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BORROWS", url = "${application.config.ms-borrows}")
public interface BorrowsExternalService {
    @GetMapping("/{idUser}")
    ResponseEntity<?> getBorrows(@PathVariable("idUser") String idUser);

    @GetMapping("/{idUser}/{idBorrow}")
    ResponseEntity<?> getBorrowById(@PathVariable("idUser") String idUser, @PathVariable("idBorrow") Long idBorrow);

    @GetMapping("/latestBorrowedBook/{idBook}")
    ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook);

    @PostMapping("/create")
    ResponseEntity<?> createBorrow(@RequestBody CreateBorrowDTO createBorrowDTO);

    @PutMapping("/update")
    ResponseEntity<?> updateBorrow(@RequestBody UpdateBorrowDTO updateBorrowDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteBorrow(@RequestBody DeleteBorrowDTO deleteBorrowDTO);
}
