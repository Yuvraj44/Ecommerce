package com.example.ECommerce.Impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ECommerce.DTO.RatingRequest;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Entity.Rating;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Repo.RatingRepo;
import com.example.ECommerce.Service.ProductService;
import com.example.ECommerce.Service.RatingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RatingImpl implements RatingService{

    private final RatingRepo ratingRepository;
    private final ProductService productService;

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}