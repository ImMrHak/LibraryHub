package com.libraryhub.shareddata.sharedRecords.msUsers.user.record.request;

import jakarta.validation.constraints.NotNull;

public record DeleteUserDTO(
        @NotNull
        String idUser
) {
}
