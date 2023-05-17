package com.biblios.huceng.usecases.book.service;


import com.biblios.huceng.bibliosentity.Author;
import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.bibliosentity.bibliosrepository.AuthorRepository;
import com.biblios.huceng.bibliosentity.bibliosrepository.BookRepository;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.entity.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final AppUserRepository appUserRepository;

    @Override
    public Book getBook(Long ISBN) {
        return bookRepository.getBookbyISBN(ISBN);
    }

    @Override
    public boolean borrowBook(Long ISBN, Long appUserID) {
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
        bookRepository.save(book);
    }

    @Override
    public void createAllBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public void deleteBook(Long bookISBN) {
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
        Pageable firstPageWithTwoElements = PageRequest.of(page, pageSize, Sort.by("totalCopies").descending());
        Page<Book> allBooks = bookRepository.findAll(firstPageWithTwoElements);

        return allBooks;
    }

    @Override
    public Page<Book> searchBooksByName(String searchTerm, int page, int size) {
        Pageable pager = PageRequest.of(page, size, Sort.by("totalCopies").descending());
        return bookRepository.findAllByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(searchTerm, searchTerm, pager);
    }

    @Override
    public boolean returnBook(long bookISBN, Long appUserID) {
        Book book = bookRepository.getBookbyISBN(bookISBN);
        AppUser appUser = appUserRepository.findById(appUserID).orElseThrow();
        if (book.getBorrowedByUsers().contains(appUser)) {
            book.setCopiesLeft(book.getCopiesLeft() + 1);
            book.getBorrowedByUsers().remove(appUser);
            return true;
        }
        return false;
    }

}
