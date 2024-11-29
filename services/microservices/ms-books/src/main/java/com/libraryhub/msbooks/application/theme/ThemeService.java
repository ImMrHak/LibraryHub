package com.libraryhub.msbooks.application.theme;

import com.libraryhub.msbooks.application.theme.request.CreateThemeDTO;
import com.libraryhub.msbooks.application.theme.request.DeleteThemeDTO;
import com.libraryhub.msbooks.application.theme.request.RecoverThemeDTO;
import com.libraryhub.msbooks.application.theme.request.UpdateThemeDTO;
import com.libraryhub.msbooks.application.theme.response.DataThemeDTO;
import com.libraryhub.msbooks.domain.theme.model.Theme;

import java.util.List;

public interface ThemeService {
    Object createTheme(CreateThemeDTO createThemeDTO);
    Object updateTheme(UpdateThemeDTO updateThemeDTO);
    Object deleteTheme(DeleteThemeDTO deleteThemeDTO);
    Object recoverTheme(RecoverThemeDTO recoverThemeDTO);
    List<DataThemeDTO> getThemes();
    DataThemeDTO getThemeById(Long id);
    Boolean existsThemeByName(String name);
    Theme findThemeByName(String name);
}
