package com.libraryhub.msusers.adapter.web;

import com.libraryhub.msusers.adapter.wrapper.ResponseWrapper;
import com.libraryhub.msusers.application.user.UserService;
import com.libraryhub.msusers.application.user.record.request.UserRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        Object data = userService.createUser(userRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

}

