package com.example.ECommerce.DTO;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long productId;
    private String review;

}