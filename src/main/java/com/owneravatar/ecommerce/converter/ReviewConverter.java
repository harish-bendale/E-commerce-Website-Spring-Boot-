package com.owneravatar.ecommerce.converter;

import com.owneravatar.ecommerce.dto.ReviewDTO;
import com.owneravatar.ecommerce.model.Review;

public class ReviewConverter {
    public static ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUser(UsersConverter.toDTO(review.getUsers()));
        return dto;
    }
}

