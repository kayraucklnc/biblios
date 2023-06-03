package com.biblios.huceng.usecases.book.controller;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.Publisher;
import com.biblios.huceng.bibliosentity.Shelf;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.repository.AppUserRepository;
import com.biblios.huceng.startup.StartupService;
import com.biblios.huceng.usecases.book.dto.BookRequest;
import com.biblios.huceng.usecases.book.service.BookService;
import com.biblios.huceng.usecases.publisher.service.PublisherService;
import com.biblios.huceng.usecases.shelf.service.ShelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(path = "/api/book") //localhost::api/post
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final StartupService startupService;
    private final PublisherService publisherService;
    private final ShelfService shelfService;


    @PostMapping()
    public void createNewPost(@RequestBody BookRequest bookRequest) {
//        bookService.createBook(bookRequest);
    }

    @GetMapping("/{page}")
    public Page<Book> returnAllBooks(@PathVariable int page) {
        return bookService.returnAllBooks(page, 10);
    }

    @GetMapping()
    public List<Book> returnAllBooks() {
        return bookService.returnAllBooks();
    }


    @GetMapping("borrow/{bookISBN}")
    public boolean borrowBook(@PathVariable long bookISBN) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.borrowBook(bookISBN, appUser.getId());
    }

    @GetMapping("return/{bookISBN}")
    public boolean returnBook(@PathVariable long bookISBN) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = startupService.getUser(auth.getName());

        return bookService.returnBook(bookISBN, appUser.getId());
    }
    @GetMapping("borrows")
    public boolean returnBook() {
        return bookService.borrows();
    }

    @PostMapping("addBook")
    public boolean addBook(@RequestBody BookRequest bookRequest) {
        Publisher publisher = publisherService.getPublisherByName("Universal Architecture Net");
        Shelf shelf = shelfService.getShelf("1");
        Book book = new Book(bookRequest.getIsbn(), bookRequest.getName(), bookRequest.getFormat(), bookRequest.getAuthor(), bookRequest.getPhotoURL(),
                bookRequest.getCopiesLeft(), bookRequest.getTotalCopies(), bookRequest.getCategory(), bookRequest.getDescription(), bookRequest.getRate(), shelf, publisher);
        bookService.createBook(book);
        return true;
    }

    @PostMapping("delete/{bookISBN}")
    public boolean addBook(@PathVariable long bookISBN) {
        bookService.deleteBook(bookISBN);
        return true;
    }
}