package com.owneravatar.ecommerce.Service;

import com.owneravatar.ecommerce.converter.ReviewConverter;
import com.owneravatar.ecommerce.dto.ReviewDTO;
import com.owneravatar.ecommerce.exception.ResourceNotFoundException;
import com.owneravatar.ecommerce.model.Product;
import com.owneravatar.ecommerce.model.Review;
import com.owneravatar.ecommerce.model.Users;
import com.owneravatar.ecommerce.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long productId, Long userId, int rating, String comment) {
        if (reviewRepository.existsByProductIdAndUserId(productId, userId)) {
            throw new IllegalStateException("User has already reviewed this product.");
        }
        Review review = new Review();
        review.setProduct(new Product(productId));
        review.setUser(new Users(userId));
        review.setRating(rating);
        review.setComment(comment);
        return reviewRepository.save(review);
    }

    public List<ReviewDTO> getAllReview() {
        return reviewRepository.findAll().stream()
                .map(ReviewConverter::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO getReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found with ID: " + id));
        return ReviewConverter.toDTO(review);
    }
}
