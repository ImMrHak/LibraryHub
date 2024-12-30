package com.libraryhub.msusers.infrastructure.FeignClients.microservices.borrowOF.borrows;

import com.libraryhub.msusers.infrastructure.FeignClients.Config.FeignClientConfig;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.CreateBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.DeleteBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.UpdateBorrowDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BORROW", url = "${application.config.ms-borrows}", configuration = FeignClientConfig.class)
public interface BorrowsExternalService {
    @GetMapping
    ResponseEntity<?> getBorrows();

    @GetMapping("/borrowedBook/{isbn}")
    ResponseEntity<?> getBorrowedBook(@PathVariable("isbn") String isbn);

    @GetMapping("/myBorrows/count")
    ResponseEntity<?> getMyBorrowsCount();

    @GetMapping("/myBorrows")
    ResponseEntity<?> getMyBorrows();

    @GetMapping("/myBorrow/{idBorrow}")
    ResponseEntity<?> getMyBorrow(@PathVariable("idBorrow") Long idBorrow);

    @GetMapping("/myReturnedBorrows/count")
    ResponseEntity<?> getMyReturnedBorrowsCount();

    @GetMapping("/latestBorrowedBook/{idBook}")
    ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook);

    @GetMapping("/{idBorrow}")
    ResponseEntity<?> getBorrowById(@PathVariable("idBorrow") Long idBorrow);

    @GetMapping("/recentReturnedBorrows")
    ResponseEntity<?> recentReturnedBorrows();


    @PostMapping("/create")
    ResponseEntity<?> createBorrow(@RequestBody CreateBorrowDTO createBorrowDTO);

    @PutMapping("/update")
    ResponseEntity<?> updateBorrow(@RequestBody UpdateBorrowDTO updateBorrowDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteBorrow(@RequestBody DeleteBorrowDTO deleteBorrowDTO);
}
