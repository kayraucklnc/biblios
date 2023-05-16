package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query("SELECT a FROM Author a ORDER BY a.ID desc")
    List<Author> getAllAuthorsByID();

    @Query("SELECT a FROM Author a WHERE a.nationality = :nationality ORDER BY a.nationality desc")
    List<Author> getAllAuthorsByNationality(String nationality);

    Page<Author> findAll(Pageable pageable);

    @Query("SELECT a FROM Author a WHERE a.ID= :ID")
    Author getAuthorByID(String ID);

    @Query("SELECT a FROM Author a WHERE a.name= :Name")
    Author getAuthorByName(String Name);

    @Query("DELETE FROM Author a WHERE a.ID= :id")
    void deleteById(String id);

    @Query("SELECT a.nationality FROM Author a WHERE a.ID= :ID")
    String getNationalityAuthor(String ID);

    //tekrar bak AuthorServiceImpl hata verdi
/*
    @Query("DELETE FROM Author a WHERE a.ID= :ID")
    void deleteByID(String ID);
*/


}
