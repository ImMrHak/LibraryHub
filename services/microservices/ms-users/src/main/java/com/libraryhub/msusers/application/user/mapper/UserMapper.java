package com.libraryhub.msusers.application.user.mapper;

import com.libraryhub.msusers.application.user.record.request.UserRequestDTO;
import com.libraryhub.msusers.application.user.record.response.UserResponseDTO;
import com.libraryhub.msusers.domain.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestDTOToUser(UserRequestDTO userRequestDto);
    UserResponseDTO userToUserResponseDTO(User user);
}
