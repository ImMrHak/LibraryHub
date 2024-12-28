package com.libraryhub.msusers.application.user.record.request;

import com.libraryhub.msusers.domain.user.enumeration.UserTypeEnum;
import jakarta.validation.constraints.NotNull;

public record CreateKeyCloakUserDTO(
        @NotNull(message = "User type must not be null")
        UserTypeEnum userType
) {
}
