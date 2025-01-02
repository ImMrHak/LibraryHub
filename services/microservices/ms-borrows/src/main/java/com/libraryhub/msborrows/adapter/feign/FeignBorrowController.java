package com.libraryhub.msborrows.adapter.feign;

import com.libraryhub.msborrows.application.BorrowService;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.GetLatestBorrowByIdBookDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.GetMyBorrowsDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.RecentReturnedBooksDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feign/v1/borrows")
@RequiredArgsConstructor
public class FeignBorrowController {
    private final BorrowService borrowService;

    @GetMapping("/myBorrows/count/{idUser}")
    public ResponseEntity<?> getMyBorrowsCount(@PathVariable("idUser") String idUser) {
        Object data = borrowService.getMyBorrowsCount(new GetMyBorrowsDTO(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReturnedBorrows/count/{idUser}")
    public ResponseEntity<?> getMyReturnedBorrowsCount(@PathVariable("idUser") String idUser) {
        Object data = borrowService.getMyReturnedBorrowsCount(new GetMyBorrowsDTO(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/recentReturnedBorrows/{idUser}")
    public ResponseEntity<?> recentReturnedBorrows(@PathVariable("idUser") String idUser) {
        Object data = borrowService.recentReturnedBorrows(new RecentReturnedBooksDTO(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/latestBorrowedBook/{idBook}")
    public ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook){
        Object data = borrowService.getLatestBorrowByIdBook(new GetLatestBorrowByIdBookDTO(idBook));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
