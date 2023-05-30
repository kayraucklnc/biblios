package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Author;
import com.biblios.huceng.bibliosentity.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PublisherRepository extends PagingAndSortingRepository<Publisher, Long> {

    @Query("SELECT p FROM Publisher p ORDER BY p.ID desc")
    List<Publisher> getAllPublishersByID();

    @Query("SELECT p FROM Publisher p WHERE p.name = :name ORDER BY p.name desc")
    List<Author> getAllPublishersByNationality(String name);

    @Query("SELECT p FROM Publisher p WHERE p.ID= :ID")
    Publisher getPublisherByID(String ID);

    @Query("SELECT p FROM Publisher p WHERE p.name= :Name")
    Publisher getPublisherByName(String Name);

    @Query("DELETE FROM Publisher p WHERE p.ID= :id")
    void deleteById(String id);

    @Query("SELECT p.name FROM Publisher p WHERE p.ID= :id")
    String getPublisherNameByID(String id);
}
