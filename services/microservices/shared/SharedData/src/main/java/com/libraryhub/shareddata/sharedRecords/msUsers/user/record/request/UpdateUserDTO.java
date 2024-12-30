package com.libraryhub.shareddata.sharedRecords.msUsers.user.record.request;

import com.libraryhub.shareddata.sharedRecords.msUsers.user.record.enumeration.UserTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        String idUser,
        @NotEmpty(message = "Field must not be empty")
        String username,
        @NotEmpty(message = "Field must not be empty")
        @Email(message="email format not valid")
        String email,
        @NotEmpty(message = "Field must not be empty")
        String firstName,
        @NotEmpty(message = "Field must not be empty")
        String lastName,
        @NotNull(message = "User type must not be null")
        UserTypeEnum userType
) {
}
