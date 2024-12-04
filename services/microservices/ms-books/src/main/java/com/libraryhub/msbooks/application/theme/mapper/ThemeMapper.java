package com.libraryhub.msbooks.application.theme.mapper;

import com.libraryhub.msbooks.application.theme.record.request.CreateThemeDTO;
import com.libraryhub.msbooks.application.theme.record.response.DataThemeDTO;
import com.libraryhub.msbooks.domain.theme.model.Theme;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThemeMapper {
    Theme mapCreateThemeDTOToTheme(CreateThemeDTO createThemeDTO);
    CreateThemeDTO mapDataThemeDTOToCreateThemeDTO(DataThemeDTO dataThemeDTO);
    DataThemeDTO mapThemeToDataThemeDTO(Theme theme);
}
