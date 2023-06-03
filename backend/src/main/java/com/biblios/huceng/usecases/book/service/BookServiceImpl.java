package com.biblios.huceng.usecases.book.service;


import com.biblios.huceng.bibliosentity.Author;
import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.bibliosrepository.AuthorRepository;
import com.biblios.huceng.bibliosentity.bibliosrepository.BookRepository;
import com.biblios.huceng.bibliosentity.bibliosrepository.LogRepository;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.repository.AppUserRepository;
import com.biblios.huceng.usecases.log.service.LogService;
import com.biblios.huceng.usecases.log.service.LogServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final LogService logService;


    private final AppUserRepository appUserRepository;

    @Override
    public Book getBook(Long ISBN) {
        return bookRepository.getBookbyISBN(ISBN);
    }

    @Override
    public boolean borrowBook(Long ISBN, Long appUserID) {
        logService.addLog("Book id with " + ISBN + " is added");
        Book book = bookRepository.getBookbyISBN(ISBN);
        if (book.getCopiesLeft() > 0) {
            AppUser appUser = appUserRepository.findById(appUserID).orElseThrow();
            book.setCopiesLeft(book.getCopiesLeft() - 1);
            book.getBorrowedByUsers().add(appUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void createBook(Book book) {
        logService.addLog("Book id with " + book.getISBN() + " is added");
        bookRepository.save(book);
    }

    @Override
    public void createAllBooks(List<Book> books) {
        logService.addLog("Books added in bulk");
        bookRepository.saveAll(books);
    }

    @Override
    public void deleteBook(Long bookISBN) {
        logService.addLog("Book id with " + bookISBN + " is deleted");
        bookRepository.deleteById(bookISBN);
    }

    @Override
    public Float getRateBook(Long bookISBN) {
        return bookRepository.getRatebyISBN(bookISBN);
    }

    @Override
    public List<Book> getAllBooksByCategory(String category) {
        return bookRepository.getAllBooksByCategory(category);
    }

    @Override
    public List<Book> getAllBooksbyISBN() {
        return bookRepository.getAllBooksByISBN();
    }

    @Override
    public Book getBookbyName(String name) {
        return bookRepository.getBookbyName(name);
    }

    @Override
    public Page<Book> returnAllBooks(int page, int pageSize) {
        Pageable firstPage = PageRequest.of(page, pageSize, Sort.by("totalCopies").descending());
        Page<Book> allBooks = bookRepository.findAll(firstPage);

        return allBooks;
    }

    @Override
    public List<Book> returnAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Page<Book> searchBooksByName(String searchTerm, int page, int size) {
        Pageable pager = PageRequest.of(page, size, Sort.by("totalCopies").descending());
        return bookRepository.findAllByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchTerm, searchTerm, pager);
    }

    @Override
    public boolean returnBook(long bookISBN, Long appUserID) {
        logService.addLog("Book id with " + bookISBN + " is returned");
        Book book = bookRepository.getBookbyISBN(bookISBN);
        AppUser appUser = appUserRepository.findById(appUserID).orElseThrow();
        if (book.getBorrowedByUsers().contains(appUser)) {
            book.setCopiesLeft(book.getCopiesLeft() + 1);
            book.getBorrowedByUsers().remove(appUser);
            return true;
        }
        return false;
    }

    public List<AppUser> getUsersBorrowingBook(Book book) {
        return appUserRepository.findAllByBorrowedByBooksContaining(book);
    }
}
