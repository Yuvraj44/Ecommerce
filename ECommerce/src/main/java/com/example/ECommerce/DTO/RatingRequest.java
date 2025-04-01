package com.example.ECommerce.DTO;

import lombok.Data;

@Data
public class RatingRequest {

    private Long productId;
    private double rating;

}