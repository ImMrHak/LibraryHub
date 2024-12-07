package com.libraryhub.msborrows.adapter.web;

import com.libraryhub.msborrows.application.BorrowService;
import com.libraryhub.msborrows.application.record.request.*;
import com.libraryhub.msborrows.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msborrows.infrastructure.usersOF.users.UsersExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrow")
@RequiredArgsConstructor
public class BorrowController {
    private final BorrowService borrowService;
    private final BooksExternalService booksExternalService;
    private final UsersExternalService usersExternalService;


    @GetMapping()
    ResponseEntity<?> getBorrow(GetBorrowDTO getBorrowDTO){
        Object data = borrowService.getBorrow(getBorrowDTO);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping()
    ResponseEntity<?> getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO){
        Object data = borrowService.getBorrowById(getBorrowByIdDTO);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/create")
    ResponseEntity<?> createBorrow(CreateBorrowDTO createBorrowDTO){
        Object data = borrowService.createBorrow(createBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateBorrow(UpdateBorrowDTO updateBorrowDTO){
        Object data = borrowService.updateBorrow(updateBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteBorrow(DeleteBorrowDTO deleteBorrowDTO){
        Object data = borrowService.deleteBorrow(deleteBorrowDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
