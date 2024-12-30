package com.libraryhub.msborrows.adapter.web;

import com.libraryhub.msborrows.application.BorrowService;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrows")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @GetMapping() @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBorrows() {
        Object data = borrowService.getBorrows();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/borrowedBook/{isbn}")
    public ResponseEntity<?> getBorrowedBook(@PathVariable("isbn") String isbn) {
        Object data = borrowService.existBorrowedBookByISBN(isbn);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myBorrows/count") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getMyBorrowsCount(Authentication authentication) {
        Object data = borrowService.getMyBorrowsCount(new GetMyBorrowsDTO(((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReturnedBorrows/count") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getMyReturnedBorrowsCount(Authentication authentication) {
        Object data = borrowService.getMyReturnedBorrowsCount(new GetMyBorrowsDTO(((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }


    @GetMapping("/myBorrows") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getMyBorrows(Authentication authentication) {
        Object data = borrowService.getMyBorrows(new GetMyBorrowsDTO(((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myBorrow/{idBorrow}") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getMyBorrow(Authentication authentication, @PathVariable("idBorrow") Long idBorrow) {
        Object data = borrowService.getMyBorrowById(new GetMyBorrowByIdDTO(idBorrow,((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    // pay attention need work
    @GetMapping("/latestBorrowedBook/{idBook}")
    public ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook){
        Object data = borrowService.getLatestBorrowByIdBook(new GetLatestBorrowByIdBookDTO(idBook));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idBorrow}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBorrowById(@PathVariable("idBorrow") Long idBorrow) {
        Object data = borrowService.getBorrowById(new GetBorrowByIdDTO(idBorrow));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/recentReturnedBorrows") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> recentReturnedBorrows(Authentication authentication) {
        Object data = borrowService.recentReturnedBorrows((new RecentReturnedBooksDTO(((Jwt) authentication.getPrincipal()).getSubject())));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }



    @PostMapping("/create") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> createBorrow(Authentication authentication, @Valid @RequestBody CreateBorrowDTO createBorrowDTO){
        Object data = borrowService.createBorrow(createBorrowDTO.withIdUser(((Jwt) authentication.getPrincipal()).getSubject()));

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/update") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> updateBorrow(Authentication authentication,@Valid @RequestBody UpdateBorrowDTO updateBorrowDTO){
        Object data = borrowService.updateBorrow(updateBorrowDTO.withIdUser(((Jwt) authentication.getPrincipal()).getSubject()));

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete") @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> deleteBorrow(@Valid @RequestBody DeleteBorrowDTO deleteBorrowDTO){
        Object data = borrowService.deleteBorrow(deleteBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
