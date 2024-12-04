package com.libraryhub.msbooks.application.theme;

import com.libraryhub.msbooks.application.theme.record.request.CreateThemeDTO;
import com.libraryhub.msbooks.application.theme.record.request.DeleteThemeDTO;
import com.libraryhub.msbooks.application.theme.record.request.UpdateThemeDTO;
import com.libraryhub.msbooks.application.theme.record.response.DataThemeDTO;
import com.libraryhub.msbooks.domain.theme.model.Theme;

import java.util.List;

public interface ThemeService {
    Object createTheme(CreateThemeDTO createThemeDTO);
    Object updateTheme(UpdateThemeDTO updateThemeDTO);
    Object deleteTheme(DeleteThemeDTO deleteThemeDTO);
    List<DataThemeDTO> getThemes();
    DataThemeDTO getThemeById(Long id);
    Boolean existsThemeByName(String name);
    Theme findThemeByName(String name);
}
