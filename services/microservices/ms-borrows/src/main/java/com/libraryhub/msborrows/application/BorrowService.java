package com.libraryhub.msborrows.application;

import com.libraryhub.msborrows.application.record.request.*;
import com.libraryhub.msborrows.application.record.response.DataBorrowDTO;

import java.util.List;

public interface BorrowService {
    Object createBorrow(CreateBorrowDTO createBorrowDTO);
    Object updateBorrow(UpdateBorrowDTO updateBorrowDTO);
    Object deleteBorrow(DeleteBorrowDTO deleteBorrowDTO);
    List<DataBorrowDTO> getBorrow(GetBorrowDTO getBorrowDTO);
    DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO);
}
