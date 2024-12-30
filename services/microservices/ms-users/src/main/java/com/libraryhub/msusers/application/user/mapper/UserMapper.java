package com.libraryhub.msusers.application.user.mapper;

import com.libraryhub.msusers.domain.user.model.User;
import com.libraryhub.shareddata.sharedRecords.msUsers.user.record.request.CreateUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestDTOToUser(CreateUserDTO createUserDto);
    com.libraryhub.shareddata.sharedRecords.msUsers.user.record.response.DataUserDTO userToDataUserDTO(User user);
}
