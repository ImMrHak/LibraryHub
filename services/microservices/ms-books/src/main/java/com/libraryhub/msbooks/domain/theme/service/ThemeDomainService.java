package com.libraryhub.msbooks.domain.theme.service;

import com.libraryhub.msbooks.domain.theme.model.Theme;
import com.libraryhub.msbooks.domain.theme.repository.ThemeDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeDomainService {
    private final ThemeDomainRepository themeDomainRepository;

    public List<Theme> findAll(){
        return themeDomainRepository.findAll();
    }

    public Theme findThemeById(Long id){
        return themeDomainRepository.findById(id).orElse(null);
    }

    public Theme saveTheme(Theme theme){
        return themeDomainRepository.save(theme);
    }

    public Boolean deleteThemeById(Long id){
        Theme dbTheme = findThemeById(id);

        if(dbTheme == null) return false;

        themeDomainRepository.delete(dbTheme);
        return true;
    }

    public Boolean deleteTheme(Theme theme){
        themeDomainRepository.save(theme);
        return true;
    }

    public Boolean existsThemeByName(String name){
        return themeDomainRepository.existsThemeByName(name);
    }

    public Boolean existsByIdTheme(Long idTheme){
        return themeDomainRepository.existsByIdTheme(idTheme);
    }

    public Theme findByName(String name){
        return themeDomainRepository.findByName(name);
    }
}
