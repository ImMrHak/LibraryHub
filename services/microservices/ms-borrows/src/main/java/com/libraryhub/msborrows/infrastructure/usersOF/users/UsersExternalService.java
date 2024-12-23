package com.libraryhub.msborrows.infrastructure.usersOF.users;


import com.libraryhub.msborrows.infrastructure.FeignClientConfig.FeignClientConfig;
import com.libraryhub.msborrows.infrastructure.usersOF.users.record.request.CreateUserDTO;
import com.libraryhub.msborrows.infrastructure.usersOF.users.record.request.DeleteUserDTO;
import com.libraryhub.msborrows.infrastructure.usersOF.users.record.request.RecoverUserDTO;
import com.libraryhub.msborrows.infrastructure.usersOF.users.record.request.UpdateUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-USERS", url = "${application.config.ms-users}", configuration = FeignClientConfig.class)
public interface UsersExternalService {

    @GetMapping
    ResponseEntity<?> getAllUsers();

    @GetMapping("/{idUser}")
    ResponseEntity<?> getUserById(@PathVariable("idUser") String idUser);

    @GetMapping("/deleteUsers")
    ResponseEntity<?> getAllDeletedUsers();

    @GetMapping("/deletedUsers/{idUser}")
    ResponseEntity<?> getDeletedUserById(@PathVariable("idUser") String idUser);

    @PostMapping("/create")
    ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO);

    @PutMapping("/update")
    ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO);

    @PutMapping("/recover")
    ResponseEntity<?> recoverUser(@RequestBody RecoverUserDTO recoverUserDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUser(@RequestBody DeleteUserDTO deleteUserDTO);
}