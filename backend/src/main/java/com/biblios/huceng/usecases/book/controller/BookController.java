package com.biblios.huceng.usecases.book.controller;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.book.dto.BookRequest;
import com.biblios.huceng.usecases.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(path = "/api/book") //localhost::api/post
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final StartupService startupService;

    @PostMapping()
    public void createNewPost(@RequestBody BookRequest bookRequest){
//        bookService.createBook(bookRequest);
    }

    @GetMapping("/{page}")
    public Page<Book> returnAllBooks(@PathVariable int page){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.returnAllBooks(page, 10);
    }

    @GetMapping("borrow/{bookISBN}")
    public boolean borrowBook(@PathVariable long bookISBN){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.borrowBook(bookISBN, appUser.getId());
    }

    @GetMapping("return/{bookISBN}")
    public boolean returnBook(@PathVariable long bookISBN){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.returnBook(bookISBN, appUser.getId());
    }
}