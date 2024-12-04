package com.libraryhub.msborrows.application;

import com.libraryhub.msborrows.application.mapper.BorrowMapper;
import com.libraryhub.msborrows.application.record.request.CreateBorrowDTO;
import com.libraryhub.msborrows.application.record.request.DeleteBorrowDTO;
import com.libraryhub.msborrows.application.record.request.UpdateBorrowDTO;
import com.libraryhub.msborrows.application.record.response.DataBorrowDTO;
import com.libraryhub.msborrows.domain.service.BorrowDomainService;
import com.libraryhub.msborrows.infrastructure.booksOF.books.BooksExternalService;
import com.libraryhub.msborrows.infrastructure.booksOF.themes.ThemesExternalService;
import com.libraryhub.msborrows.infrastructure.usersOF.users.UsersExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {
    private final BorrowDomainService borrowDomainService;
    private final BorrowMapper borrowMapper;
    private final BooksExternalService booksExternalService;
    private final ThemesExternalService themesExternalService;
    private final UsersExternalService usersExternalService;

    @Override
    public Object createBorrow(CreateBorrowDTO createBorrowDTO) {

        return null;
    }

    @Override
    public Object updateBorrow(UpdateBorrowDTO updateBorrowDTO) {

        return null;
    }

    @Override
    public Object deleteBorrow(DeleteBorrowDTO deleteBorrowDTO) {
        return null;
    }

    @Override
    public List<DataBorrowDTO> getBorrow() {
        return List.of();
    }

    @Override
    public DataBorrowDTO getBorrowById(Long id) {
        return null;
    }
}
