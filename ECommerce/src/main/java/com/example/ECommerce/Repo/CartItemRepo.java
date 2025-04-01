package com.example.ECommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.CartItem;
import com.example.ECommerce.Entity.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart=:cart AND ci.product=:product AND ci.size=:size AND ci.userId=:userId")
    CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product, @Param("size") String size, @Param("userId") Long userId);

}
