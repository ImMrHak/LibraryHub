package com.libraryhub.msborrows.application.borrow;

import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.request.*;
import com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.response.DataBorrowDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BorrowService {
    Object createBorrow(CreateBorrowDTO createBorrowDTO);
    Object updateBorrow(UpdateBorrowDTO updateBorrowDTO);
    Object deleteBorrow(Authentication authentication, DeleteBorrowDTO deleteBorrowDTO);
    List<DataBorrowDTO> getBorrows();
    Boolean existBorrowedBookByISBN(String isbn);
    Integer getMyBorrowsCount(GetMyBorrowsDTO getMyBorrowsDTO);
    Integer getMyReturnedBorrowsCount(GetMyBorrowsDTO getMyBorrowsDTO);
    DataBorrowDTO getBorrowById(GetBorrowByIdDTO getBorrowByIdDTO);
    DataBorrowDTO getMyBorrowById(GetMyBorrowByIdDTO getMyBorrowByIdDTO);
    DataBorrowDTO getLatestBorrowByIdBook(GetLatestBorrowByIdBookDTO getLatestBorrowByIdBookDTO);
    List<DataBorrowDTO> getMyBorrows(GetMyBorrowsDTO getMyBorrowsDTO);
    List<DataBorrowDTO> recentReturnedBorrows(RecentReturnedBooksDTO recentReturnedBooksDTO);
    Object returnMyBorrowedBookById(Authentication authentication, UpdateMyBorrow updateMyBorrow);
}
