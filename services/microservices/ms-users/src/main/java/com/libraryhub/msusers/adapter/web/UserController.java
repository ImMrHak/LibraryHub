package com.libraryhub.msusers.adapter.web;

import com.libraryhub.msusers.adapter.wrapper.ResponseWrapper;
import com.libraryhub.msusers.application.user.UserService;
import com.libraryhub.msusers.application.user.record.request.CreateUserDTO;
import com.libraryhub.msusers.application.user.record.request.DeleteUserDTO;
import com.libraryhub.msusers.application.user.record.request.UpdateUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    ResponseEntity<?> getAllUsers() {
        Object data = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @GetMapping("/{idUser}")
    ResponseEntity<?> getUserById(@PathVariable("idUser") String idUser) {
        Object data = userService.getUserById(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @GetMapping("/deleteUsers")
    public ResponseEntity<?> getAllDeleteUsers() {
        Object data = userService.getDeletedUsers();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @GetMapping("/deletedUsers/{idUser}")
    public ResponseEntity<?> getDeletedUsersById(@PathVariable("idUser") String idUser) {
        Object data = userService.getDeletedUserById(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        Object data = userService.createUser(createUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO){
        Object data = userService.updateUser(updateUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUserDTO deleteUserDTO){
        Object data = userService.deleteUser(deleteUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }


}


