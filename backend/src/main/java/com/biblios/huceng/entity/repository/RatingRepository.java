package com.biblios.huceng.entity.repository;

import com.biblios.huceng.entity.ProfileRatingKey;
import com.biblios.huceng.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, ProfileRatingKey> {

}
