package com.biblios.huceng.bibliosentity.bibliosrepository;


import com.biblios.huceng.bibliosentity.Campus;
import com.biblios.huceng.bibliosentity.Shelf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ShelfRepository extends PagingAndSortingRepository<Shelf, Long> {

    @Query("SELECT s FROM Shelf s ORDER BY s.ID desc")
    List<Shelf> getAllShelfByID();


    Page<Shelf> findAll(Pageable pageable);

    @Query("SELECT s FROM Shelf s WHERE s.ID= :ID")
    Shelf getShelfByID(String ID);


    @Query("DELETE FROM Campus c WHERE c.ID= :id")
    void deleteById(String id);







}
