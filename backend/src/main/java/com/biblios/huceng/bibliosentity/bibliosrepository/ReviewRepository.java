package com.biblios.huceng.bibliosentity.bibliosrepository;

import com.biblios.huceng.entity.Report;
import com.biblios.huceng.bibliosentity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r.reviews FROM Book r WHERE r.ISBN= :ISBN")
    Collection<Review> getReviewsByISBN(Long ISBN);
}
