package com.libraryhub.msbooks.application.book.mapper;

import com.libraryhub.msbooks.application.book.record.request.CreateBookDTO;
import com.libraryhub.msbooks.application.book.record.response.DataBookDTO;
import com.libraryhub.msbooks.domain.book.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book mapCreateBookDTOToBook(CreateBookDTO createBookDTO);
    DataBookDTO mapBookToDataBookDTO(Book book);
}
