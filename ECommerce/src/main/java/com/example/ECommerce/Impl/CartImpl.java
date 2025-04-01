package com.example.ECommerce.Impl;

import org.springframework.stereotype.Service;

import com.example.ECommerce.DTO.AddItemRequest;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.CartItem;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Repo.CartRepo;
import com.example.ECommerce.Service.CartItemService;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartImpl implements CartService{

    private final CartRepo cartRepository;
    private final CartItemService cartItemService;
    private final ProductService productService;


    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart,product, req.getSize(), userId);

        if(isPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            int price = req.getQuantity() * product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            
            cart.getCartItems().add(createdCartItem);
        }
        return "item add to cart";
    }



    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);

        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;

        for(CartItem cartItem: cart.getCartItems()){
           totalPrice = totalPrice + cartItem.getPrice();
           totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
           totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice - totalDiscountedPrice);

        return cartRepository.save(cart);
    }
}