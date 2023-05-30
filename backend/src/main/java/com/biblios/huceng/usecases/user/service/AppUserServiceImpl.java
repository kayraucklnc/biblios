package com.biblios.huceng.usecases.user.service;


import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.bibliosrepository.AuthorRepository;
import com.biblios.huceng.bibliosentity.bibliosrepository.BookRepository;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Override
    public Page<Book> getBorrowedBooksForAppUserWithID(Long id, int pageNumber) {
        AppUser appUser = appUserRepository.findAppUserById(id).orElseThrow();
        Pageable pageable = PageRequest.of(pageNumber, 10);

        Page<Book> bookPage = convertToPage(appUser.getBorrowedByBooks(), pageable);
        return bookPage;
    }

    public Page<Book> convertToPage(Collection<Book> books, Pageable pageable) {
        List<Book> bookList = new ArrayList<>(books);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), bookList.size());

        return new PageImpl<>(bookList.subList(start, end), pageable, bookList.size());
    }

    @Override
    public Long getIdFromProfileName(String profilename){
        AppUser appUser = appUserRepository.findAppUserByUsername(profilename).orElseThrow();
        return appUser.getId();
    }
}
