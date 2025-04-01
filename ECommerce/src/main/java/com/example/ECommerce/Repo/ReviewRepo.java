package com.example.ECommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ECommerce.Entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.product.id=:productId")
    List<Review> getAllProductReview(@Param("productId") Long productId);

}
