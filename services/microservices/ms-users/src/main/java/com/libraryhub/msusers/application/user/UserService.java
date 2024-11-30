package com.libraryhub.msusers.application.user;

import com.libraryhub.msusers.application.user.record.request.DeleteUserDTO;
import com.libraryhub.msusers.application.user.record.request.RecoverUserDTO;
import com.libraryhub.msusers.application.user.record.request.UpdateUserDTO;
import com.libraryhub.msusers.application.user.record.request.CreateUserDTO;
import com.libraryhub.msusers.application.user.record.response.DataUserDTO;

import java.util.List;

public interface UserService {
    Object createUser(CreateUserDTO createUserDto);
    Object updateUser(UpdateUserDTO updateUserDTO);
    Object deleteUser(DeleteUserDTO deleteUserDTO);
    Object recoverUser(RecoverUserDTO recoverUserDTO);
    List<DataUserDTO> getUsers();
    DataUserDTO getUserById(String id);
    List<DataUserDTO> getDeletedUsers();
    DataUserDTO getDeletedUserById(String id);
}
