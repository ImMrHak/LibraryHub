package com.libraryhub.msbooks.application.book;

import com.libraryhub.msbooks.application.book.mapper.BookMapper;
import com.libraryhub.msbooks.application.book.request.*;
import com.libraryhub.msbooks.application.book.response.DataBookDTO;
import com.libraryhub.msbooks.application.theme.ThemeService;
import com.libraryhub.msbooks.application.theme.mapper.ThemeMapper;
import com.libraryhub.msbooks.application.theme.request.CreateThemeDTO;
import com.libraryhub.msbooks.application.theme.request.DeleteThemeDTO;
import com.libraryhub.msbooks.application.theme.response.DataThemeDTO;
import com.libraryhub.msbooks.domain.book.model.Book;
import com.libraryhub.msbooks.domain.book.service.BookDomainService;
import com.libraryhub.msbooks.domain.theme.model.Theme;
import com.libraryhub.msbooks.domain.theme.service.ThemeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDomainService bookDomainService;
    private final ThemeDomainService themeDomainService;
    private final BookMapper bookMapper;
    private final ThemeMapper themeMapper;

    @Override
    public Object createBook(CreateBookDTO createBookDTO) {
        if(bookDomainService.existsBookByTitle(createBookDTO.title())) return "Book already exists";

        Set<Theme> themes = new HashSet<>();

        for(CreateThemeDTO themeDTO : createBookDTO.themes()) {
            if(themeDomainService.existsThemeByName(themeDTO.name())) themes.add(themeDomainService.findByName(themeDTO.name()));

            else {
                Theme newTheme = themeMapper.mapCreateThemeDTOToTheme(themeDTO);
                themeDomainService.saveTheme(newTheme);
                themes.add(newTheme);
            }
        }

        Book dbBook = bookMapper.mapCreateBookDTOToBook(createBookDTO);
        dbBook.setThemes(themes);
        dbBook.setIsAvailable(true);
        dbBook.setIsDeleted(false);

        return bookMapper.mapBookToDataBookDTO(bookDomainService.saveBook(dbBook));
    }

    @Override
    public Object updateBook(UpdateBookDTO updateBookDTO) {
        if(!bookDomainService.existsByIdBook(updateBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(updateBookDTO.idBook());

        if(dbBook.getIsDeleted()) return "Book does not exist because it's deleted";

        dbBook.setTitle(updateBookDTO.title());
        dbBook.setAuthor(updateBookDTO.author());
        dbBook.setIsbn(updateBookDTO.isbn());
        dbBook.setPublicationYear(updateBookDTO.publicationYear());

        Set<Theme> themes = new HashSet<>();

        for(CreateThemeDTO themeDTO : updateBookDTO.themes()) {
            if(themeDomainService.existsThemeByName(themeDTO.name())) themes.add(themeDomainService.findByName(themeDTO.name()));

            else {
                Theme newTheme = themeMapper.mapCreateThemeDTOToTheme(themeDTO);
                themeDomainService.saveTheme(newTheme);
                themes.add(newTheme);
            }
        }

        dbBook.setThemes(themes);

        return bookMapper.mapBookToDataBookDTO(bookDomainService.saveBook(dbBook));
    }

    @Override
    public Object deleteBook(DeleteBookDTO deleteBookDTO) {
        if(!bookDomainService.existsByIdBook(deleteBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(deleteBookDTO.idBook());

        if(dbBook.getIsDeleted()) return "Book is already deleted";

        dbBook.setIsDeleted(true);

        return bookDomainService.deleteBook(dbBook);
    }

    @Override
    public Object recoverBook(RecoverBookDTO recoverBookDTO) {
        if(!bookDomainService.existsByIdBook(recoverBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(recoverBookDTO.idBook());

        if(!dbBook.getIsDeleted()) return "Book is already not deleted";

        dbBook.setIsDeleted(false);

        bookDomainService.saveBook(dbBook);
        return true;
    }

    @Override
    public List<DataBookDTO> getBooks() {
        return bookDomainService.findAll().stream().filter(b -> !b.getIsDeleted()).map(bookMapper::mapBookToDataBookDTO).collect(Collectors.toList());
    }

    @Override
    public DataBookDTO getBookById(Long id) {
        Book dbBook = bookDomainService.findBookById(id);

        if(dbBook.getIsDeleted()) return null;

        return bookMapper.mapBookToDataBookDTO(dbBook);
    }

    @Override
    public List<DataBookDTO> getDeletedBooks() {
        return bookDomainService.findAll().stream().filter(Book::getIsDeleted).map(bookMapper::mapBookToDataBookDTO).collect(Collectors.toList());
    }

    @Override
    public DataBookDTO getDeletedBookById(Long id) {
        Book dbBook = bookDomainService.findBookById(id);

        if(!dbBook.getIsDeleted()) return null;

        return bookMapper.mapBookToDataBookDTO(dbBook);
    }

    @Override
    public Object addThemeToBook(AddThemeToBookDTO addThemeToBookDTO) {
        if(!bookDomainService.existsByIdBook(addThemeToBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(addThemeToBookDTO.idBook());

        Set<Theme> themes = dbBook.getThemes();

        for(CreateThemeDTO themeDTO : addThemeToBookDTO.themes()) {
            if(themeDomainService.existsThemeByName(themeDTO.name())) themes.add(themeDomainService.findByName(themeDTO.name()));

            else {
                Theme newTheme = themeMapper.mapCreateThemeDTOToTheme(themeDTO);
                themeDomainService.saveTheme(newTheme);
                themes.add(newTheme);
            }
        }

        dbBook.setThemes(themes);

        return bookMapper.mapBookToDataBookDTO(bookDomainService.saveBook(dbBook));
    }

    @Override
    public Object deleteThemeFromBook(DeleteThemeFromBookDTO deleteThemeFromBookDTO) {
        if(!bookDomainService.existsByIdBook(deleteThemeFromBookDTO.idBook())) return "Book does not exist";

        Book dbBook = bookDomainService.findBookById(deleteThemeFromBookDTO.idBook());

        for(DeleteThemeDTO themeDTO : deleteThemeFromBookDTO.themes()){
            dbBook.getThemes().removeIf(theme -> theme.getIdTheme().equals(themeDTO.idTheme()));
        }

        return bookMapper.mapBookToDataBookDTO(bookDomainService.saveBook(dbBook));
    }
}
