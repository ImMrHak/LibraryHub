package com.libraryhub.msborrows.application.record.request;

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
}
