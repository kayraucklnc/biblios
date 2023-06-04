package com.biblios.huceng.usecases.book.service;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.entity.AppUser;
import com.biblios.huceng.usecases.book.dto.Borrows;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface BookService {

    Book getBook(Long ISBN);

    boolean borrowBook(Long ISBN, Long appUserID);

    void createBook(Book book);

    void createAllBooks(List<Book> books);

    void deleteBook(Long bookISBN);

    Float getRateBook(Long bookISBN);

    List<Book> getAllBooksByCategory(String category);

    List<Book> getAllBooksbyISBN();

    Book getBookbyName(String name);

    Page<Book> returnAllBooks(int page, int pageSize);

    List<Book> returnAllBooks();

    Page<Book> searchBooksByName(String searchTerm, int page, int size);

    boolean returnBook(long bookISBN, Long appUserID);

    List<AppUser> getUsersBorrowingBook(Book book);

    List<Borrows> borrows();
}
