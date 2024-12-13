package com.libraryhub.msborrows.adapter.web;

import com.libraryhub.msborrows.application.BorrowService;
import com.libraryhub.msborrows.application.record.request.*;
import com.libraryhub.msborrows.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msborrows.infrastructure.usersOF.users.UsersExternalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrows")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;

    @GetMapping()
    public ResponseEntity<?> getBorrows() {
        Object data = borrowService.getBorrows();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
    @GetMapping("/myBorrows/{idUser}")
    public ResponseEntity<?> getMyBorrows(@PathVariable("idUser") String idUser) {
        Object data = borrowService.getMyBorrows(new GetMyBorrowsDTO(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myBorrow/{idUser}/{idBorrow}")
    public ResponseEntity<?> getMyBorrow(@PathVariable("idUser") String idUser, @PathVariable("idBorrow") Long idBorrow) {
        Object data = borrowService.getMyBorrowById(new GetMyBorrowByIdDTO(idBorrow,idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/latestBorrowedBook/{idBook}")
    public ResponseEntity<?> getLatestBorrowByIdBook(@PathVariable("idBook") Long idBook){
        Object data = borrowService.getLatestBorrowByIdBook(new GetLatestBorrowByIdBookDTO(idBook));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idBorrow}")
    public ResponseEntity<?> getBorrowById(@PathVariable("idBorrow") Long idBorrow) {
        Object data = borrowService.getBorrowById(new GetBorrowByIdDTO(idBorrow));
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }



    @PostMapping("/create")
    public ResponseEntity<?> createBorrow(@Valid @RequestBody CreateBorrowDTO createBorrowDTO){
        Object data = borrowService.createBorrow(createBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBorrow(@Valid @RequestBody UpdateBorrowDTO updateBorrowDTO){
        Object data = borrowService.updateBorrow(updateBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBorrow(@Valid @RequestBody DeleteBorrowDTO deleteBorrowDTO){
        Object data = borrowService.deleteBorrow(deleteBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
