package com.libraryhub.msborrows.application.record.request;

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
