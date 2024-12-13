package com.libraryhub.msreservations.adapter.web;

import com.libraryhub.msreservations.application.reservation.ReservationService;
import com.libraryhub.msreservations.application.reservation.record.request.*;
import com.libraryhub.msreservations.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msreservations.infrastructure.booksOF.themes.ThemesExternalService;
import com.libraryhub.msreservations.infrastructure.usersOF.users.UsersExternalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final BooksExternalService booksExternalService;
    private final ThemesExternalService themesExternalService;
    private final UsersExternalService usersExternalService;

    @GetMapping()
    public ResponseEntity<?> getReservations() {
        Object data = reservationService.getReservations();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations/{idUser}")
    public ResponseEntity<?> getMyReservations(@PathVariable("idUser") String idUser) {
        Object data = reservationService.getMyReservations(new GetMyReservations(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/activeReservations")
    public ResponseEntity<?> getActiveReservations() {
        Object data = reservationService.getActiveReservations();

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations/{idUser}/myActiveReservations")
    public ResponseEntity<?> getMyActiveReservations(@PathVariable("idUser") String idUser) {
        Object data = reservationService.getMyActiveReservations(new GetMyActiveReservations(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/{idReservation}")
    public ResponseEntity<?> getReservation(@PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getReservationById(new GetReservation(idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservation/{idUser}/{idReservation}")
    public ResponseEntity<?> getMyReservation(@PathVariable("idUser") String idUser, @PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getMyReservationById(new GetMyReservationUser(idUser, idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/activeReservations/{idReservation}")
    public ResponseEntity<?> getActiveReservation(@PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getActiveReservationById(new GetActiveReservation(idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping("/myReservations/{idUser}/myActiveReservation/{idReservation}")
    public ResponseEntity<?> getMyActiveReservation(@PathVariable("idUser") String idUser, @PathVariable("idReservation") Long idReservation) {
        Object data = reservationService.getMyActiveReservationById(new GetMyActiveReservation(idUser, idReservation));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@Valid @RequestBody CreateReservationDTO createReservationDTO) {
        Object data = reservationService.createReservation(createReservationDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBorrow(@Valid @RequestBody DeleteReservationDTO deleteReservationDTO){
        Object data = reservationService.deleteReservation(deleteReservationDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body((String) data);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
