package com.example.ECommerce.Service;

import java.util.List;

import com.example.ECommerce.DTO.RatingRequest;
import com.example.ECommerce.Entity.Rating;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;

public interface RatingService {

    Rating createRating(RatingRequest req, User user) throws ProductException;

    List<Rating> getProductsRating(Long productId);

}