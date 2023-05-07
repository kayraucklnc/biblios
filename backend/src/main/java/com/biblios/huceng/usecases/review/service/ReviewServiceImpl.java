package com.biblios.huceng.usecases.review.service;

import com.biblios.huceng.bibliosentity.Review;
import com.biblios.huceng.bibliosentity.bibliosrepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }
}
