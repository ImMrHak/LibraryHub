package com.libraryhub.msborrows.application.mapper;

import com.libraryhub.msborrows.application.record.request.CreateBorrowDTO;
import com.libraryhub.msborrows.application.record.response.DataBorrowDTO;
import com.libraryhub.msborrows.domain.model.Borrow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowMapper {
    Borrow mapCreateBorrowDTOToBorrow(CreateBorrowDTO createBorrowDTO);
    DataBorrowDTO mapBorrowToDataBorrowDTO(Borrow borrow);
}
