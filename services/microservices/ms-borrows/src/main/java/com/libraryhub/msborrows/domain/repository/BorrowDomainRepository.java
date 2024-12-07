package com.libraryhub.msborrows.domain.repository;

import com.libraryhub.msborrows.domain.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowDomainRepository extends JpaRepository<Borrow, Long> {
    Boolean existsByIdBorrow(Long id);
}
