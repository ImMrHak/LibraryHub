package com.libraryhub.msusers.application.user.record.response;


import com.libraryhub.msusers.domain.user.enumeration.UserTypeEnum;

public record DataUserDTO(
        String idUser,
        String firstName,
        String lastName,
        String email,
        String username,
        UserTypeEnum userType
) {
}
