package com.libraryhub.msusers.application.user;

import com.libraryhub.msusers.application.user.record.request.UserRequestDTO;
import com.libraryhub.msusers.application.user.record.response.UserResponseDTO;
import com.libraryhub.msusers.domain.user.model.User;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDto);
/*    UserResponseDTO updateUser(updateuserdto);
    String deleteUser(String userId);
    List<UserResponseDTO> getAllUser();
    UserResponseDTO getUserById(String userId);
*/
}
