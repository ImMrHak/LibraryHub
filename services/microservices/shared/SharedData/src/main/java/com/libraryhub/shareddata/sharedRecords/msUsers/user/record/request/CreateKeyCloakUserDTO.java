package com.libraryhub.shareddata.sharedRecords.msUsers.user.record.request;

import com.libraryhub.shareddata.sharedRecords.msUsers.user.record.enumeration.UserTypeEnum;
import jakarta.validation.constraints.NotNull;

public record CreateKeyCloakUserDTO(
        @NotNull(message = "User type must not be null")
        UserTypeEnum userType
) {
}
