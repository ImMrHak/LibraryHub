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
    private final BooksExternalService booksExternalService;
    private final UsersExternalService usersExternalService;


    @PostMapping()
    public ResponseEntity<?> getBorrow(@Valid @RequestBody GetBorrowDTO getBorrowDTO){
        Object data = borrowService.getBorrow(getBorrowDTO);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/d")
    public ResponseEntity<?> getBorrowById(@Valid @RequestBody GetBorrowByIdDTO getBorrowByIdDTO){
        Object data = borrowService.getBorrowById(getBorrowByIdDTO);
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
