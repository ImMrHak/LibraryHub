package com.libraryhub.msborrows.application.borrow.mapper;

import com.libraryhub.msborrows.domain.borrow.model.Borrow;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.CreateBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.response.DataBorrowDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowMapper {
    Borrow mapCreateBorrowDTOToBorrow(CreateBorrowDTO createBorrowDTO);
    DataBorrowDTO mapBorrowToDataBorrowDTO(Borrow borrow);
}
