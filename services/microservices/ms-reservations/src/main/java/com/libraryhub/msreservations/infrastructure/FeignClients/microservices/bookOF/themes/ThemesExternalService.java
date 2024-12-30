package com.libraryhub.msreservations.infrastructure.FeignClients.microservices.bookOF.themes;

import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.CreateThemeDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.DeleteThemeDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.theme.record.request.UpdateThemeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "MS-BOOKS", url = "${application.config.ms-themes}", configuration = FeignClientsConfiguration.class)
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
