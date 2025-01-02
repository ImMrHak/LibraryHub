package com.libraryhub.msreservations.infrastructure.FeignClients.microservices.usersOF.users;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-USERS", url = "${application.config.ms-users}")
public interface UsersExternalService {
    @GetMapping("/{idUser}")
    ResponseEntity<?> getUserById(@PathVariable("idUser") String idUser);
}