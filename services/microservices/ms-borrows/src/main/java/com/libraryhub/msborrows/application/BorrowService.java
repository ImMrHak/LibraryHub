package com.libraryhub.msborrows.application;

import com.libraryhub.msborrows.application.record.request.*;
import com.libraryhub.msborrows.application.record.response.DataBorrowDTO;

import java.util.List;

public interface BorrowService {
    Object createBorrow(CreateBorrowDTO createBorrowDTO);
    Object updateBorrow(UpdateBorrowDTO updateBorrowDTO);
    Object deleteBorrow(DeleteBorrowDTO deleteBorrowDTO);
    List<DataBorrowDTO> getBorrows(GetBorrowDTO getBorrowDTO);
    DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO);
    DataBorrowDTO getLatestBorrowByIdBook(GetLatestBorrowByIdBookDTO getLatestBorrowByIdBookDTO);
}
