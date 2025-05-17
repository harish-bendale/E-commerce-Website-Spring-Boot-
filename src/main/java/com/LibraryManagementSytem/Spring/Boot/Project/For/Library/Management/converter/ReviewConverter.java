package com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.converter;

import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.dto.ReviewDTO;
import com.LibraryManagementSytem.Spring.Boot.Project.For.Library.Management.model.Review;

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

