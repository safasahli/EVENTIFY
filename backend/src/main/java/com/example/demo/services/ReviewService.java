package com.example.demo.services;

import com.example.demo.entities.Review;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {
    Review createReview(Review review);
    Review getReviewById(Long id);
    List<Review> getAllReviews();
    List<Review> getReviewsByProduct(Long productId);
    List<Review> getReviewsByUser(Long userId);
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
}
