package com.libraryhub.msborrows.application.mapper;

import com.libraryhub.msborrows.domain.model.Borrow;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.CreateBorrowDTO;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.response.DataBorrowDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowMapper {
    Borrow mapCreateBorrowDTOToBorrow(CreateBorrowDTO createBorrowDTO);
    DataBorrowDTO mapBorrowToDataBorrowDTO(Borrow borrow);
}
