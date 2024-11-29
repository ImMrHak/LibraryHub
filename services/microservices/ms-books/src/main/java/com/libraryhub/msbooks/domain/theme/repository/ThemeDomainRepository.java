package com.libraryhub.msbooks.domain.theme.repository;

import com.libraryhub.msbooks.domain.theme.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeDomainRepository extends JpaRepository<Theme, Long> {
}
