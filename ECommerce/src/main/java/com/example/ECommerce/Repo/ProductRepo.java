package com.example.ECommerce.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ECommerce.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
           "WHERE (:category IS NULL OR :category = '' OR p.category.name = :category) " +
           "AND (:minPrice IS NULL OR :maxPrice IS NULL OR p.discountedPrice BETWEEN :minPrice AND :maxPrice) " +
           "AND (:minDiscount IS NULL OR p.discountPersent >= :minDiscount) " +
           "ORDER BY " +
           "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
           "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC")
    Page<Product> filterProducts(@Param("category") String category,
                                 @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,
                                 @Param("minDiscount") Integer minDiscount,
                                 @Param("sort") String sort,
                                 Pageable pageable);
}
