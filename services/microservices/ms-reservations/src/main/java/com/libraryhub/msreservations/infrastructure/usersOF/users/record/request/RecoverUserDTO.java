package com.libraryhub.msreservations.infrastructure.usersOF.users.record.request;

import jakarta.validation.constraints.NotNull;

public record RecoverUserDTO(
        @NotNull
        String idUser
) {
}
