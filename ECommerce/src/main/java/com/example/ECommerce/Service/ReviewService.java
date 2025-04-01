package com.example.ECommerce.Service;

import java.util.List;

import com.example.ECommerce.DTO.ReviewRequest;
import com.example.ECommerce.Entity.Review;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;

public interface ReviewService {

    Review createReview(ReviewRequest req, User user) throws ProductException;
    List<Review> getAllReview(Long productId);

}