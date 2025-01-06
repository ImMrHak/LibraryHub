package com.libraryhub.msborrows.domain.borrow.repository;

import com.libraryhub.msborrows.domain.borrow.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowDomainRepository extends JpaRepository<Borrow, Long> {
    Boolean existsByIdBorrow(Long id);
    Borrow findFirstByIdBookOrderByReturnDate(Long idBook);
    List<Borrow> findAllByIdUser(String idUser);
    List<Borrow> findBorrowsByIdUser(String idUser);
    Borrow findBorrowByIdBorrowAndIdUser(Long idBorrow, String idUser);
    Integer countBorrowsByIdUser(String idUser);
    List<Borrow> findAllByIdBookOrderByReturnDateDesc(Long idBook);
    Integer countBorrowByIdUserAndActualReturnDateIsNotNull(String idUser);
    List<Borrow> findAllByIdUserAndActualReturnDateNotNullOrderByActualReturnDateDesc(String idUser);
}
