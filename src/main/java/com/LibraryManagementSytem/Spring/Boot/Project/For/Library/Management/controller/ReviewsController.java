package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.controller;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.Service.ReviewService;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.CreateReviewDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ProductDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ReviewDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewDTO dto) {
        try {
            Review review = reviewService.addReview(dto.getProductId(), dto.getUserId(), dto.getRating(), dto.getComment());
            return ResponseEntity.ok(review);
        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping()
    public List<ReviewDTO> findAllReviews() {
        return reviewService.getAllReview();
    }

    @GetMapping("/{reviewId}")
    public ReviewDTO findReviewById(@PathVariable Long reviewId) {
        return reviewService.getReview(reviewId);
    }
}
