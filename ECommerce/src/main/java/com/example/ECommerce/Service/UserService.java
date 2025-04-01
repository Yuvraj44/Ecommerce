package com.example.ECommerce.Service;


import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.UserException;

public interface UserService {
	 User findUserById(Long userId) throws UserException;

	 User findUserProfileByJwt(String jwt) throws UserException;
}
