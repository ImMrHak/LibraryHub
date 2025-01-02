package com.libraryhub.msbooks.application.book.mapper;

import com.libraryhub.msbooks.domain.book.model.Book;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.request.CreateBookDTO;
import com.libraryhub.shareddata.sharedRecords.msBooks.book.record.response.DataBookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book mapCreateBookDTOToBook(CreateBookDTO createBookDTO);
    DataBookDTO mapBookToDataBookDTO(Book book);
}
