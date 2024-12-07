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

    public Borrow findBorrowById(Long id){
        return borrowDomainRepository.findById(id).orElse(null);
    }

    public Borrow saveBorrow(Borrow borrow){
        return borrowDomainRepository.save(borrow);
    }

    public Boolean deleteBorrowById(Long id){
        borrowDomainRepository.deleteById(id);
        return true;
    }

    public Boolean deleteBorrow(Borrow dbBorrow){
        borrowDomainRepository.delete(dbBorrow);
        return true;
    }

    public Boolean existsByIdBorrow(Long idBorrow){
        return borrowDomainRepository.existsById(idBorrow);
    }
}
