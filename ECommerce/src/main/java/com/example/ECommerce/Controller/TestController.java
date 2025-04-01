package com.example.ECommerce.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.example.ECommerce.Service.RatingService;
import com.example.ECommerce.Service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
