package com.libraryhub.msborrows.domain.service;

import com.libraryhub.msborrows.domain.model.Borrow;
import com.libraryhub.msborrows.domain.repository.BorrowDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowDomainService {
    private final BorrowDomainRepository borrowDomainRepository;

    public List<Borrow> findAll(){
        return borrowDomainRepository.findAll();
    }

    public Borrow findReservationById(Long id){
        return borrowDomainRepository.findById(id).orElse(null);
    }

    public Borrow saveReservation(Borrow reservation){
        return borrowDomainRepository.save(reservation);
    }

    public Boolean deleteReservationById(Long id){
        borrowDomainRepository.deleteById(id);
        return true;
    }

    public Boolean deleteReservation(Borrow dbReservation){
        borrowDomainRepository.delete(dbReservation);
        return true;
    }
}
