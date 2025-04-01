package com.example.ECommerce.Service;

import com.example.ECommerce.DTO.AddItemRequest;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;

public interface CartService {

    Cart createCart(User user);

    String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    Cart findUserCart(Long userId);


}