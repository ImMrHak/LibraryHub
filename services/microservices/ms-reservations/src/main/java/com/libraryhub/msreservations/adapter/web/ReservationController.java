package com.libraryhub.msreservations.adapter.web;

import com.libraryhub.msreservations.application.reservation.ReservationService;
import com.libraryhub.msreservations.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msreservations.infrastructure.booksOF.themes.ThemesExternalService;
import com.libraryhub.msreservations.infrastructure.usersOF.users.UsersExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final BooksExternalService booksExternalService;
    private final ThemesExternalService themesExternalService;
    private final UsersExternalService usersExternalService;
}
