package com.libraryhub.msreservations.adapter.feign;

import com.libraryhub.msreservations.application.reservation.ReservationService;
import com.libraryhub.shareddata.sharedRecords.msReservations.reservation.record.request.GetMyReservationsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feign/v1/reservations")
@RequiredArgsConstructor
public class FeignReservationController {
    private final ReservationService reservationService;

    @GetMapping("/myReservations/count/{idUser}")
    public ResponseEntity<?> getMyReservationsCount(@PathVariable("idUser") String idUser) {
        Object data = reservationService.getMyReservationsCount(new GetMyReservationsDTO(idUser));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
}
