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

    public List<Reservation> findAllByIdUser(String idUser){
        return reservationDomainRepository.findAllByIdUser(idUser);
    }

    public List<Reservation> findAllByIsActive(Boolean isActive){
        return reservationDomainRepository.findAllByIsActive(isActive);
    }

    public List<Reservation> findAllByIdUserAndIsActive(String idUser, Boolean isActive){
        return reservationDomainRepository.findAllByIdUserAndIsActive(idUser, isActive);
    }

    public Boolean existsByIdReservation(Long idReservation){
        return reservationDomainRepository.existsById(idReservation);
    }

    public Reservation findReservationByIdReservationAndIsActive(Long idReservation, Boolean isActive){
        return reservationDomainRepository.findReservationByIdReservationAndIsActive(idReservation, isActive);
    }

    public Reservation findReservationByIdReservationAndIdUser(Long idReservation, String idUser){
        return reservationDomainRepository.findReservationByIdReservationAndIdUser(idReservation, idUser);
    }

    public Reservation findReservationByIdReservationAndIdUserAndIsActive(Long idReservation, String idUser, Boolean isActive){
        return findReservationByIdReservationAndIdUserAndIsActive(idReservation, idUser, isActive);
    }

    public Integer countAllByIdUser(String idUser, Boolean isActive){
        return  reservationDomainRepository.countAllByIdUserAndIsActive(idUser, isActive);
    }
}
