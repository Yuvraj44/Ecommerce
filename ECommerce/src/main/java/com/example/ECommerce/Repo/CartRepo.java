package com.example.ECommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ECommerce.Entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.id=:userId")
    Cart findByUserId(@Param("userId") Long userId);
}
