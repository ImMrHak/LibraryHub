package com.libraryhub.shareddata.sharedRecords.msBorrows.borrow.record.response;

import java.util.Date;

public record DataBorrowDTO(
         Long idBorrow,

         Date borrowDate,

         Date returnDate,

         Date actualReturnDate,

         String idUser,

         Long idBook
) {
}
