package com.owneravatar.ecommerce.repo;

import com.owneravatar.ecommerce.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);

    @Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.product.id = :productId AND r.users.id = :userId")
    boolean existsByProductIdAndUserId(Long productId, Long userId);
}
