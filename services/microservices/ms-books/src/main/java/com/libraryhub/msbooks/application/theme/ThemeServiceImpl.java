package com.libraryhub.msbooks.application.theme;

import com.libraryhub.msbooks.domain.theme.repository.ThemeDomainRepository;
import com.libraryhub.msbooks.domain.theme.service.ThemeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeDomainService themeDomainService;
}
