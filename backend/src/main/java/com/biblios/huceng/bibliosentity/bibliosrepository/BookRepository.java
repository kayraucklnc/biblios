package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Book;
import com.biblios.huceng.usecases.book.dto.Borrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>  {

    @Query("SELECT b FROM Book b ORDER BY b.ISBN desc")
    List<Book> getAllBooksByISBN();

    @Query("SELECT b FROM Book b WHERE b.category = :category ORDER BY b.category desc")
    List<Book> getAllBooksByCategory(String category);

    @Query("SELECT DISTINCT b FROM Book b")
    List<Book> getAllBooks( );

    Page<Book> findAll(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.ISBN= :ISBN")
    Book getBookbyISBN(Long ISBN);

    @Query("SELECT b FROM Book b WHERE b.name= :Name")
    Book getBookbyName(String Name);


    @Query("SELECT b.rate FROM Book b WHERE b.ISBN= :ISBN")
    Float getRatebyISBN(Long ISBN);

    @Query("SELECT u.username,b.name as bookname FROM Book b JOIN b.borrowedByUsers u")
    List<Object[]> getISBNOfBorrowedBooks();


    Page<Book> findAllByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author, Pageable pager);

}
