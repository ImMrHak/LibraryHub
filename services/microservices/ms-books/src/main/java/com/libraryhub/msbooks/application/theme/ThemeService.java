package com.libraryhub.msbooks.application.theme;

import com.libraryhub.msbooks.domain.theme.model.Theme;
import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.CreateThemeDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.DeleteThemeDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.UpdateThemeDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.response.DataThemeDTO;

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
