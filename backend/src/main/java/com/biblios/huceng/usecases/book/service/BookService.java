package com.biblios.huceng.usecases.book.service;

import com.biblios.huceng.bibliosentity.Book;

import java.util.List;

public interface BookService {

    Book getBook(Long ISBN);

    void createBook(Book book);

    void createAllBooks(List<Book> books);

    void deleteBook(Long bookISBN);

    Float getRateBook(Long bookISBN);


    List<Book> getAllBooksByCategory(String category);


    List<Book> getAllBooksbyISBN();

    Book getBookbyName(String name);

}
