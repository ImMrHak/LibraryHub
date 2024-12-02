package com.libraryhub.msreservations.domain.repository;

import com.libraryhub.msreservations.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDomainRepository extends JpaRepository<Reservation, Long> {
}
