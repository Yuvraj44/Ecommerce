package com.example.ECommerce.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.DTO.ReviewRequest;
import com.example.ECommerce.Entity.Review;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Exception.UserException;
import com.example.ECommerce.Service.ReviewService;
import com.example.ECommerce.Service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final UserService userService;
    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(req, user);

        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }


    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId,
                                                          @RequestHeader("Authorization") String jwt) throws UserException, ProductException {

        List<Review> reviews = reviewService.getAllReview(productId);

        return new ResponseEntity<>(reviews, HttpStatus.ACCEPTED);
    }


}