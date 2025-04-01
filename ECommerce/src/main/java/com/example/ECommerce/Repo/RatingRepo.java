package com.example.ECommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ECommerce.Entity.Rating;



public interface RatingRepo extends JpaRepository<Rating,Long> {

    @Query("SELECT r FROM Rating r WHERE r.product.id=:productId")
    List<Rating> getAllProductsRating(@Param("productId") Long productId);

}