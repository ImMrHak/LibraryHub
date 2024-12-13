package com.libraryhub.msreservations.infrastructure.borrowsOF.borrows.record.response;

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
