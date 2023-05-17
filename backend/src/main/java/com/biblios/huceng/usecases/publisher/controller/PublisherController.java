package com.biblios.huceng.usecases.publisher.controller;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.book.dto.BookRequest;
import com.biblios.huceng.usecases.book.service.BookService;
import com.biblios.huceng.usecases.publisher.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/publisher") //localhost::api/post
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;
    private final StartupService startupService;

    @PostMapping()
    public void createNewPost(@RequestBody BookRequest bookRequest){
//        bookService.createBook(bookRequest);
    }

/*    @GetMapping("/{page}")
    public Page<Book> returnAllBooks(@PathVariable int page){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.returnAllBooks(page, 10);
    }*/
}
