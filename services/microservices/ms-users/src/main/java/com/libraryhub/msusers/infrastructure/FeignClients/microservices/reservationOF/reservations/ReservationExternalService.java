package com.libraryhub.msusers.infrastructure.FeignClients.microservices.reservationOF.reservations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-RESERVATIONS", url = "${application.config.ms-reservations}")
public interface ReservationExternalService {
    @GetMapping("/myReservations/count/{idUser}")
    ResponseEntity<?> getMyReservationsCount(@PathVariable("idUser") String idUser);
}
