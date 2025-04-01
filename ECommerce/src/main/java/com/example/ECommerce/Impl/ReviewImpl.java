 package com.example.ECommerce.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ECommerce.DTO.ReviewRequest;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Entity.Review;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Repo.ReviewRepo;
import com.example.ECommerce.Service.ProductService;
import com.example.ECommerce.Service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewImpl implements ReviewService{

    private final ReviewRepo reviewRepository;
    private final ProductService productService;

    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllProductReview(productId);
    }
}