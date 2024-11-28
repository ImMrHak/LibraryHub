package com.libraryhub.msusers.application.user;

import com.libraryhub.msusers.application.user.mapper.UserMapper;
import com.libraryhub.msusers.application.user.record.request.UserRequestDTO;
import com.libraryhub.msusers.application.user.record.response.UserResponseDTO;
import com.libraryhub.msusers.domain.user.model.User;
import com.libraryhub.msusers.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDomainService userDomainService;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDto) {
        User dbUser = userDomainService.saveUser(userMapper.userRequestDTOToUser(userRequestDto));
        return userMapper.userToUserResponseDTO(dbUser);
    }
}
