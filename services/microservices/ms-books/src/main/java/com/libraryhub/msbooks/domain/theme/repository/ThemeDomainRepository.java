package com.libraryhub.msbooks.domain.theme.repository;

import com.libraryhub.msbooks.domain.theme.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeDomainRepository extends JpaRepository<Theme, Long> {
    Boolean existsThemeByName(String name);
    Boolean existsByIdTheme(Long idTheme);
    Theme findByName(String name);
}
