package com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.record.request;

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
}
