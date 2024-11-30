package com.libraryhub.msusers.application.user.mapper;

import com.libraryhub.msusers.application.user.record.request.CreateUserDTO;
import com.libraryhub.msusers.application.user.record.response.DataUserDTO;
import com.libraryhub.msusers.domain.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestDTOToUser(CreateUserDTO createUserDto);
    DataUserDTO userToDataUserDTO(User user);
}
