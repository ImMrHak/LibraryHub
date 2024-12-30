package com.libraryhub.shareddata.sharedRecords.msUsers.user.record.response;

import com.libraryhub.shareddata.sharedRecords.msUsers.user.record.enumeration.UserTypeEnum;

public record DataUserDTO(
        String idUser,
        String firstName,
        String lastName,
        String email,
        String username,
        UserTypeEnum userType
) {
}
