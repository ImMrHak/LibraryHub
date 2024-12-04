package com.libraryhub.msborrows.application.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateBorrowDTO(
        @NotNull
        Date borrowDate,
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
