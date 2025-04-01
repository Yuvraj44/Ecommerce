package com.example.ECommerce.Controller;


import com.example.ECommerce.DTO.AddItemRequest;
import com.example.ECommerce.DTO.ApiResponse;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.CartItem;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.CartItemException;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Exception.UserException;
import com.example.ECommerce.Repo.CartItemRepo;
import com.example.ECommerce.Service.CartItemService;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final CartItemService cartItemService;
    // Endpoint to find a user's cart based on their JWT token
    @GetMapping("")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // Endpoint to add an item to the cart
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
                                                     @RequestHeader("Authorization") String jwt)
                                                     throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), req);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Endpoint to update a cart item
    @PutMapping("/items/{userId}/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable Long userId,
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem
    ) {
        try {
            CartItem updatedCartItem = cartItemService.updateCartItem(userId, cartItemId, cartItem);
            return new ResponseEntity<>(updatedCartItem, HttpStatus.OK);
        } catch (CartItemException | UserException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    
    @DeleteMapping("/items/{userId}/{cartItemId}")
    public ResponseEntity<String> removeCartItem(
            @PathVariable Long userId, 
            @PathVariable Long cartItemId
    ) {
        try {
            cartItemService.removeCartItem(userId, cartItemId);
            return new ResponseEntity<>("CartItem removed successfully", HttpStatus.NO_CONTENT);
        } catch (CartItemException | UserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to find a cart item by its ID
    @GetMapping("/items/{cartItemId}")
    public ResponseEntity<CartItem> findCartItemById(@PathVariable Long cartItemId) {
        try {
            CartItem cartItem = cartItemService.findCartItemById(cartItemId);
            return new ResponseEntity<>(cartItem, HttpStatus.OK);
        } catch (CartItemException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
