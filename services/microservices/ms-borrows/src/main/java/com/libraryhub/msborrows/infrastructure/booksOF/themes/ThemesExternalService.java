package com.libraryhub.msborrows.infrastructure.booksOF.themes;

import com.libraryhub.msborrows.infrastructure.FeignClientConfig.FeignClientConfig;
import com.libraryhub.msborrows.infrastructure.booksOF.themes.record.request.CreateThemeDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.themes.record.request.DeleteThemeDTO;
import com.libraryhub.msborrows.infrastructure.booksOF.themes.record.request.UpdateThemeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BOOKS", url = "${application.config.ms-themes}", configuration = FeignClientConfig.class)
public interface ThemesExternalService {
    @GetMapping
    ResponseEntity<?> getAllThemes();

    @GetMapping("/{idTheme}")
    ResponseEntity<?> getThemeById(@PathVariable("idTheme") Long idTheme);

    @PostMapping("/create")
    ResponseEntity<?> createTheme(@RequestBody CreateThemeDTO createThemeDTO);

    @PutMapping("/update")
    ResponseEntity<?> updateTheme(@RequestBody UpdateThemeDTO updateThemeDTO);

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteTheme(@RequestBody DeleteThemeDTO deleteThemeDTO);
}