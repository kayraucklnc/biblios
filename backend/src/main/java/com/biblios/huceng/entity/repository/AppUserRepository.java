package com.biblios.huceng.entity.repository;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {

    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserById(Long id);

    @Query("select username from AppUser")
    List<String> getAllUsernames();

    Page<AppUser> findAll(Pageable pageable);

    List<AppUser> findAllByBorrowedByBooksContaining(Book book);
}
