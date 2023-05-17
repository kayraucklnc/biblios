package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Campus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CampusRepository extends PagingAndSortingRepository<Campus, Long> {

    @Query("SELECT c FROM Campus c ORDER BY c.ID desc")
    List<Campus> getAllCampusByID();


    Page<Campus> findAll(Pageable pageable);

    @Query("SELECT c FROM Campus c WHERE c.ID= :ID")
    Campus getCampusByID(String ID);

    @Query("SELECT c FROM Campus c WHERE c.name= :Name")
    Campus getCampusByName(String Name);

    @Query("SELECT c FROM Campus c ORDER BY c.name desc")
    List<Campus> getAllCampusByName();

    @Query("DELETE FROM Campus c WHERE c.ID= :id")
    void deleteById(String id);





}
