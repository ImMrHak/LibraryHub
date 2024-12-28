package com.libraryhub.msborrows.application;

import com.libraryhub.msborrows.application.record.request.*;
import com.libraryhub.msborrows.application.record.response.DataBorrowDTO;

import java.util.List;

public interface BorrowService {
    Object createBorrow(CreateBorrowDTO createBorrowDTO);
    Object updateBorrow(UpdateBorrowDTO updateBorrowDTO);
    Object deleteBorrow(DeleteBorrowDTO deleteBorrowDTO);
    List<DataBorrowDTO> getBorrows();
    Boolean existBorrowedBookByISBN(String isbn);
    Integer getMyBorrowsCount(GetMyBorrowsDTO getMyBorrowsDTO);
    Integer getMyReturnedBorrowsCount(GetMyBorrowsDTO getMyBorrowsDTO);
    DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO);
    DataBorrowDTO getMyBorrowById(GetMyBorrowByIdDTO getMyBorrowByIdDTO);
    DataBorrowDTO getLatestBorrowByIdBook(GetLatestBorrowByIdBookDTO getLatestBorrowByIdBookDTO);
    List<DataBorrowDTO> getMyBorrows(GetMyBorrowsDTO  getMyBorrowsDTO);

}
