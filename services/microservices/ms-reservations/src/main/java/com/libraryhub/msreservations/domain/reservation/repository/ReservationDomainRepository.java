package com.libraryhub.msreservations.domain.reservation.repository;

import com.libraryhub.msreservations.domain.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDomainRepository extends JpaRepository<Reservation, Long> {
    Boolean existsByIdReservation(Long idReservation);
    List<Reservation> findAllByIdUser(String idUser);
    List<Reservation> findAllByIsActive(Boolean isActive);
    List<Reservation> findAllByIdUserAndIsActive(String idUser, Boolean isActive);
    Reservation findReservationByIdReservationAndIsActive(Long idReservation, Boolean isActive);
    Reservation findReservationByIdReservationAndIdUser(Long idReservation, String idUser);
    Reservation findReservationByIdReservationAndIdUserAndIsActive(Long idReservation, String idUser, Boolean isActive);
    Integer countAllByIdUserAndIsActive(String idUser, Boolean isActive);
}
