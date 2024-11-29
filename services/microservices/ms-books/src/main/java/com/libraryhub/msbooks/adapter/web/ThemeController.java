package com.libraryhub.msbooks.adapter.web;

import com.libraryhub.msbooks.adapter.wrapper.ResponseWrapper;
import com.libraryhub.msbooks.application.book.request.*;
import com.libraryhub.msbooks.application.theme.ThemeService;
import com.libraryhub.msbooks.application.theme.request.CreateThemeDTO;
import com.libraryhub.msbooks.application.theme.request.DeleteThemeDTO;
import com.libraryhub.msbooks.application.theme.request.UpdateThemeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/themes")
@RequiredArgsConstructor
public class ThemeController {
    private final ThemeService themeService;

    @GetMapping()
    public ResponseEntity<?> getAllThemes() {
        return ResponseEntity.ok(themeService.getThemes());
    }

    @GetMapping("/{idTheme}")
    public ResponseEntity<?> getThemeById(@PathVariable("idTheme") Long idTheme) {
        Object data = themeService.getThemeById(idTheme);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTheme(@Valid @RequestBody CreateThemeDTO createThemeDTO) {
        Object data = themeService.createTheme(createThemeDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTheme(@Valid @RequestBody UpdateThemeDTO updateThemeDTO) {
        Object data = themeService.updateTheme(updateThemeDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTheme(@Valid @RequestBody DeleteThemeDTO deleteThemeDTO) {
        Object data = themeService.deleteTheme(deleteThemeDTO);

        if(data instanceof String) return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.error((String) data));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(data));
    }
}
