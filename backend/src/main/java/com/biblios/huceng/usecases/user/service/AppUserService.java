package com.biblios.huceng.usecases.user.service;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface AppUserService {

    public Page<Book> getBorrowedBooksForAppUserWithID(Long id, int pageNumber);
    Long getIdFromProfileName(String profilename);
}
