package com.libraryhub.msbooks.application.book.mapper;

import com.libraryhub.msbooks.application.book.request.CreateBookDTO;
import com.libraryhub.msbooks.application.book.response.DataBookDTO;
import com.libraryhub.msbooks.domain.book.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book mapCreateBookDTOToBook(CreateBookDTO createBookDTO);
    DataBookDTO mapBookToDataBookDTO(Book book);
}
