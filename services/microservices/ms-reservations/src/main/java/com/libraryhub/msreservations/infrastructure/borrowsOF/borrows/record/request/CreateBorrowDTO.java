package com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateBorrowDTO(
        @NotNull
        Date borrowDate,
        @NotNull
        Date returnDate,
        @NotNull
        String idUser,
        @NotNull
        Long idBook
) {
}
