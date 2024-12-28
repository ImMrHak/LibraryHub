package com.libraryhub.msusers.infrastructure.borrowOF.borrows.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateBorrowDTO(
        @NotNull
        Long idBorrow,
        @NotNull
        Date returnDate,
        @NotNull
        Date actualReturnDate,
        @NotNull
        String idUser,
        @NotNull
        Long idBook
) {
        public UpdateBorrowDTO withIdUser(String newIdUser) {
                return new UpdateBorrowDTO(this.idBorrow,this.returnDate, this.actualReturnDate,newIdUser, this.idBook);
        }

}
