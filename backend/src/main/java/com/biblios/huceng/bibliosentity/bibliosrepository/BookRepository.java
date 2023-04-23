package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b ORDER BY b.ISBN desc")
    List<Book> getAllBooksByISBN();

    @Query("SELECT b FROM Book b WHERE b.category = :category ORDER BY b.category desc")
    List<Book> getAllBooksByCategory(String category);

    //Book getBookByISBN(Long ISBN);

    @Query("SELECT b FROM Book b WHERE b.ISBN= :ISBN")
    Book getBookbyISBN(Long ISBN);

    @Query("SELECT b FROM Book b WHERE b.name= :Name")
    Book getBookbyName(String Name);


    @Query("SELECT b.rate FROM Book b WHERE b.ISBN= :ISBN")
    Float getRatebyISBN(Long ISBN);
}