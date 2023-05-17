package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.bibliosentity.Author;
import com.biblios.huceng.bibliosentity.Series;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SeriesRepository extends PagingAndSortingRepository<Series, Long> {

    @Query("SELECT s FROM Series s ORDER BY s.ID desc")
    List<Series> getAllSeriesByID();

    Page<Series> findAll(Pageable pageable);

    @Query("SELECT s FROM Series s WHERE s.ID= :ID")
    Series getSeriesByID(String ID);

    @Query("SELECT s FROM Series s WHERE s.name= :Name")
    Series getSeriesByName(String Name);

    @Query("DELETE FROM Series s WHERE s.ID= :id")
    void deleteById(String id);

    @Query("SELECT s FROM Series s WHERE s.ID= :ID")
    String getSeriesNameByID(String ID);

}
