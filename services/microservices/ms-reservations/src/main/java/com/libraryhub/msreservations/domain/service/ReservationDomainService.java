package com.libraryhub.msreservations.domain.service;

import com.libraryhub.msreservations.domain.model.Reservation;
import com.libraryhub.msreservations.domain.repository.ReservationDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDomainService {
    private final ReservationDomainRepository reservationDomainRepository;

    public List<Reservation> findAll(){
        return reservationDomainRepository.findAll();
    }

    public Reservation findReservationById(Long id){
        return reservationDomainRepository.findById(id).orElse(null);
    }

    public Reservation saveReservation(Reservation reservation){
        return reservationDomainRepository.save(reservation);
    }

    public Boolean deleteReservationById(Long id){
        reservationDomainRepository.deleteById(id);
        return true;
    }

    public Boolean deleteReservation(Reservation dbReservation){
        reservationDomainRepository.delete(dbReservation);
        return true;
    }
}
