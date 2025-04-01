package com.example.ECommerce.Service;

import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.CartItem;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Exception.CartItemException;
import com.example.ECommerce.Exception.UserException;

public interface CartItemService {

    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    CartItem findCartItemById(Long cartItemId) throws CartItemException;

}