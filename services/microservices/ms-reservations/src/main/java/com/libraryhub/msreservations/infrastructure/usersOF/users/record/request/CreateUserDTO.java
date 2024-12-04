package com.libraryhub.msreservations.infrastructure.usersOF.users.record.request;

import com.libraryhub.msreservations.infrastructure.usersOF.users.record.enumeration.UserTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(
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
