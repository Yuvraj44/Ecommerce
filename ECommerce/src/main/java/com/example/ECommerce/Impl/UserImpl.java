package com.example.ECommerce.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.UserException;
import com.example.ECommerce.Repo.UserRepo;
import com.example.ECommerce.SecurityConfig.jwtService;
import com.example.ECommerce.Service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserImpl implements UserService{
	private final UserRepo userRepository;
	private final jwtService jwtServiceobj;


    
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
          return user.get();
        }
        throw new UserException("user not found with id: " + userId);
    }

    
    public User findUserProfileByJwt(String jwt) throws UserException {
        
        String phoneNumber = jwtServiceobj.extractUsername(jwt);
        User user = userRepository.findByPhoneNumber(phoneNumber).get();
//      User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);


        if(user == null){
            throw new UserException("user not found with phone number " + phoneNumber);
        }
        return user;
    }
}
