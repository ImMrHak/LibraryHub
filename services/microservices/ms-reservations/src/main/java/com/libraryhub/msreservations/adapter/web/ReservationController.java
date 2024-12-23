package com.libraryhub.msreservations.adapter.web;

import com.libraryhub.msreservations.application.reservation.ReservationService;
import com.libraryhub.msreservations.application.reservation.record.request.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping() @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReservations() {
        Object data = reservationService.getReservations();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations/count") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyReservationsCount(Authentication authentication) {
        Object data = reservationService.getMyReservationsCount(new GetMyReservationsDTO(((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyReservations(Authentication authentication) {
        Object data = reservationService.getMyReservations(new GetMyReservationsDTO(((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/activeReservations") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getActiveReservations() {
        Object data = reservationService.getActiveReservations();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations/myActiveReservations") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyActiveReservations(Authentication authentication) {
        Object data = reservationService.getMyActiveReservations(new GetMyActiveReservationsDTO(((Jwt) authentication.getPrincipal()).getSubject()));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idReservation}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getReservation(@PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getReservationById(new GetReservationDTO(idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservation/{idReservation}") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyReservation(Authentication authentication, @PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getMyReservationById(new GetMyReservationUserDTO(((Jwt) authentication.getPrincipal()).getSubject(), idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/activeReservations/{idReservation}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getActiveReservation(@PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getActiveReservationById(new GetActiveReservationDTO(idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations/myActiveReservation/{idReservation}") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMyActiveReservation(Authentication authentication, @PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getMyActiveReservationById(new GetMyActiveReservationDTO(((Jwt) authentication.getPrincipal()).getSubject(), idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/create") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> createReservation(Authentication authentication, @Valid @RequestBody CreateReservationDTO createReservationDTO) {
        Object data = reservationService.createReservation(createReservationDTO.withIdUser(((Jwt) authentication.getPrincipal()).getSubject()));

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @DeleteMapping("/delete") @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> deleteBorrow(@Valid @RequestBody DeleteReservationDTO deleteReservationDTO){
        Object data = reservationService.deleteReservation(deleteReservationDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
