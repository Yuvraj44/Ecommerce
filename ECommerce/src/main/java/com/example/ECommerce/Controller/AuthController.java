package com.example.ECommerce.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.DTO.AuthRequest;
import com.example.ECommerce.DTO.AuthResponse;
import com.example.ECommerce.DTO.RegisterRequest;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Repo.UserRepo;
import com.example.ECommerce.SecurityConfig.jwtService;
import com.example.ECommerce.Service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager; 
    private final UserRepo repoobj;
    private final PasswordEncoder passwordEncoder;
    private final jwtService jwtServiceobj;
    private final CartService cartService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest request) {
    	authenticationManager.authenticate
    										(new UsernamePasswordAuthenticationToken
    																				(request.getPhoneNumber(), request.getPassword()));
		var user=repoobj.findByPhoneNumber(request.getPhoneNumber()).orElseThrow();
		var jwtToken = jwtServiceobj.generateToken(user);
		
		return ResponseEntity.ok(AuthResponse.builder().token(jwtToken).build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) {
    	
//    	if (repoobj.findByPhoneNumber(request.getPhoneNumber()).isPresent()) 
//    		return ResponseEntity.ok(AuthResponse.builder().token("User already exists").build());
//    	if (repoobj.findByEmail(request.getEmail()).isPresent()) 
//    		return ResponseEntity.ok(AuthResponse.builder().token("Email already used by another user").build());
//    	
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repoobj.save(user);
        Cart cart=cartService.createCart(user);
        var jwtToken = jwtServiceobj.generateToken(user);
        return ResponseEntity.ok(AuthResponse.builder().token(jwtToken).build());
    }
}