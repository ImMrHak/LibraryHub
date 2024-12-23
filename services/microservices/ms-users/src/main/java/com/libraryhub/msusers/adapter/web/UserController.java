package com.libraryhub.msusers.adapter.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.libraryhub.msusers.application.user.UserService;
import com.libraryhub.msusers.application.user.record.request.CreateUserDTO;
import com.libraryhub.msusers.application.user.record.request.DeleteUserDTO;
import com.libraryhub.msusers.application.user.record.request.RecoverUserDTO;
import com.libraryhub.msusers.application.user.record.request.UpdateUserDTO;
import com.libraryhub.msusers.application.user.record.response.DataUserDTO;
import com.libraryhub.msusers.domain.user.enumeration.UserTypeEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.json.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/myTotalDashboardInformation")
    public ResponseEntity<?> getMyTotalDashboardInformation(Authentication authentication) {
        return null;
    }

    @GetMapping("/accountExist") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getUserAccountExist(Authentication authentication) {
        Object data = userService.getUserById(((Jwt) authentication.getPrincipal()).getClaims().get("sub").toString());

        return ResponseEntity.status(HttpStatus.OK).body(data != null);
    }

    @PostMapping("/createUserFromKeyCloak") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> createKeyCloakUser(Authentication authentication, @Valid @RequestBody CreateUserDTO createUserDTO) {
        CreateUserDTO actualTokenData = new CreateUserDTO(((Jwt) authentication.getPrincipal()).getClaims().get("sub").toString(), ((Jwt) authentication.getPrincipal()).getClaims().get("preferred_username").toString(), ((Jwt) authentication.getPrincipal()).getClaims().get("email").toString(), ((Jwt) authentication.getPrincipal()).getClaims().get("given_name").toString(), ((Jwt) authentication.getPrincipal()).getClaims().get("family_name").toString(), createUserDTO.userType());

        if(!actualTokenData.equals(createUserDTO)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Naughty Naughty Naughty");

        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(actualTokenData));
    }

    @GetMapping() @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers() {
        Object data = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idUser}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable("idUser") String idUser) {
        Object data = userService.getUserById(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/deleteUsers") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllDeleteUsers() {
        Object data = userService.getDeletedUsers();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/deletedUsers/{idUser}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getDeletedUsersById(@PathVariable("idUser") String idUser) {
        Object data = userService.getDeletedUserById(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }


    //keycloak depandency need lot of work
    @PostMapping("/create") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        Object data = userService.createUser(createUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/update") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserDTO updateUserDTO){
        Object data = userService.updateUser(updateUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PutMapping("/recover")
    public ResponseEntity<?> recoverUser(@Valid @RequestBody RecoverUserDTO recoverUserDTO){
        Object data = userService.recoverUser(recoverUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping("/delete") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUserDTO deleteUserDTO){
        Object data = userService.deleteUser(deleteUserDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

}


