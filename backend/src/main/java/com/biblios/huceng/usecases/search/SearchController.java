package com.biblios.huceng.usecases.searching.controller;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.usecases.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/search/")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

    final private BookService bookService;

    @PostMapping("{term}/{page}")
    public Page<Book> postContainsText(@PathVariable String term, @PathVariable int page) {
        return bookService.searchBooksByName(term, page, 10);
    }
}