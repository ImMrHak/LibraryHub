package com.libraryhub.msborrows.infrastructure.usersOF.users.record.response;


import com.libraryhub.msborrows.infrastructure.usersOF.users.record.enumeration.UserTypeEnum;

public record DataUserDTO(
        String idUser,
        String firstName,
        String lastName,
        String email,
        String username,
        UserTypeEnum userType
) {
}
