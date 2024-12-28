package com.libraryhub.msusers.infrastructure.reservationOF.reservations;

import com.libraryhub.msusers.infrastructure.FeignClientConfig.FeignClientConfig;
import com.libraryhub.msusers.infrastructure.reservationOF.reservations.record.request.CreateReservationDTO;
import com.libraryhub.msusers.infrastructure.reservationOF.reservations.record.request.DeleteReservationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-RESERVATIONS", url = "${application.config.ms-reservations}", configuration = FeignClientConfig.class)
public interface ReservationExternalService {

    @GetMapping
    ResponseEntity<?> getReservations();

    @GetMapping("/myReservations/count")
    ResponseEntity<?> getMyReservationsCount();

    @GetMapping("/myReservations")
    ResponseEntity<?> getMyReservations();

    @GetMapping("/activeReservations")
    ResponseEntity<?> getActiveReservations();

    @GetMapping("/myReservations/myActiveReservations")
    ResponseEntity<?> getMyActiveReservations();

    @GetMapping("/{idReservation}")
    ResponseEntity<?> getReservation(@PathVariable("idReservation") Long idReservation);

    @GetMapping("/myReservation/{idReservation}")
    ResponseEntity<?> getMyReservation(@PathVariable("idReservation") Long idReservation);

    @GetMapping("/activeReservations/{idReservation}")
    ResponseEntity<?> getActiveReservation(@PathVariable("idReservation") Long idReservation);

    @GetMapping("/myReservations/myActiveReservation/{idReservation}")
    ResponseEntity<?> getMyActiveReservation(@PathVariable("idReservation") Long idReservation);

    @PostMapping("/create")
    ResponseEntity<?> createReservation(@RequestBody CreateReservationDTO createReservationDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteReservation(@RequestBody DeleteReservationDTO deleteReservationDTO);
}
