package com.libraryhub.msborrows.application.record.request;

import jakarta.validation.constraints.NotNull;

public record GetMyBorrowsDTO(
        @NotNull
        String idUser

) {
}
