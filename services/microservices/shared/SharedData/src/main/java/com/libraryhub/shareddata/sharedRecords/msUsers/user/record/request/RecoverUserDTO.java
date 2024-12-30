package com.libraryhub.shareddata.sharedRecords.msUsers.user.record.request;

import jakarta.validation.constraints.NotNull;

public record RecoverUserDTO(
        @NotNull
        String idUser
) {
}
