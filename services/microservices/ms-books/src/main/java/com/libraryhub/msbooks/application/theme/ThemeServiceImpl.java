package com.libraryhub.msbooks.application.theme;

import com.libraryhub.msbooks.application.theme.mapper.ThemeMapper;
import com.libraryhub.msbooks.application.theme.request.CreateThemeDTO;
import com.libraryhub.msbooks.application.theme.request.DeleteThemeDTO;
import com.libraryhub.msbooks.application.theme.request.RecoverThemeDTO;
import com.libraryhub.msbooks.application.theme.request.UpdateThemeDTO;
import com.libraryhub.msbooks.application.theme.response.DataThemeDTO;
import com.libraryhub.msbooks.domain.theme.model.Theme;
import com.libraryhub.msbooks.domain.theme.service.ThemeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    private final ThemeDomainService themeDomainService;
    private final ThemeMapper themeMapper;

    @Override
    public Object createTheme(CreateThemeDTO createThemeDTO) {
        if(themeDomainService.existsThemeByName(createThemeDTO.name())) return "Theme already exists";

        return themeMapper.mapThemeToDataThemeDTO(themeDomainService.saveTheme(themeMapper.mapCreateThemeDTOToTheme(createThemeDTO)));
    }

    @Override
    public Object updateTheme(UpdateThemeDTO updateThemeDTO) {
        if(!themeDomainService.existsByIdTheme(updateThemeDTO.idTheme())) return "Theme does not exist";

        Theme dbTheme = themeDomainService.findThemeById(updateThemeDTO.idTheme());

        if(dbTheme.getIsDeleted()) return "Theme does not exist because it's deleted";

        dbTheme.setName(updateThemeDTO.name());

        return themeMapper.mapThemeToDataThemeDTO(themeDomainService.saveTheme(dbTheme));
    }

    @Override
    public Object deleteTheme(DeleteThemeDTO deleteThemeDTO) {
        if(!themeDomainService.existsByIdTheme(deleteThemeDTO.idTheme())) return "Theme does not exist";

        Theme dbTheme = themeDomainService.findThemeById(deleteThemeDTO.idTheme());

        if(dbTheme.getIsDeleted()) return "Theme is already deleted";

        dbTheme.setIsDeleted(true);

        return themeDomainService.deleteTheme(dbTheme);
    }

    @Override
    public Object recoverTheme(RecoverThemeDTO recoverThemeDTO) {
        if(!themeDomainService.existsByIdTheme(recoverThemeDTO.idTheme())) return "Theme does not exist";

        Theme dbTheme = themeDomainService.findThemeById(recoverThemeDTO.idTheme());

        if(!dbTheme.getIsDeleted()) return "Theme is already not deleted";

        dbTheme.setIsDeleted(false);

        themeDomainService.saveTheme(dbTheme);
        return true;
    }

    @Override
    public List<DataThemeDTO> getThemes() {
        return themeDomainService.findAll().stream().map(themeMapper::mapThemeToDataThemeDTO).collect(Collectors.toList());
    }

    @Override
    public DataThemeDTO getThemeById(Long id) {
        return themeMapper.mapThemeToDataThemeDTO(themeDomainService.findThemeById(id));
    }

    @Override
    public Boolean existsThemeByName(String name) {
        return themeDomainService.existsThemeByName(name);
    }

    @Override
    public Theme findThemeByName(String name) {
        return themeDomainService.findByName(name);
    }
}
