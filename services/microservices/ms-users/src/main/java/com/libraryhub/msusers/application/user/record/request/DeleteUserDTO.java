package com.libraryhub.msusers.application.user.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteUserDTO(
        @NotNull
        String idUser
) {
}
