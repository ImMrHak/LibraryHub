package com.libraryhub.msborrows.application.record.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DeleteBorrowDTO(
        @NotNull
        Long idBorrow
) {
}
