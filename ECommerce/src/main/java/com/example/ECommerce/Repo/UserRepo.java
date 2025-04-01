package com.example.ECommerce.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ECommerce.Entity.User;



public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findByPhoneNumber(String phoneNumber);
	Optional<User> findByEmail(String email);
}
