package com.libraryhub.msusers.infrastructure.borrowOF.borrows.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateBorrowDTO(
        @NotNull
        Date returnDate,
        @NotNull
        String idUser,
        @NotNull
        Long idBook
) {
        public CreateBorrowDTO withIdUser(String newIdUser) {
                return new CreateBorrowDTO(this.returnDate, newIdUser, this.idBook);
        }
}
