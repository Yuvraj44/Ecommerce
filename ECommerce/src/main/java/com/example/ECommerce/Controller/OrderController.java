package com.example.ECommerce.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.Entity.Address;
import com.example.ECommerce.Entity.Order;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.OrderException;
import com.example.ECommerce.Exception.UserException;
import com.example.ECommerce.Service.OrderService;
import com.example.ECommerce.Service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

   private final OrderService orderService;
   private UserService userService;


   @PostMapping("/")
   public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
                                            @RequestHeader("Authorization") String jwt) throws UserException {

       User user = userService.findUserProfileByJwt(jwt);
       Order order = orderService.createOrder(user,shippingAddress);

       System.out.println("order " + order);

       return new ResponseEntity<Order>(order, HttpStatus.CREATED);
   }


   @GetMapping("/user")
   public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserException{
      User user = userService.findUserProfileByJwt(jwt);
      List<Order> orders = orderService.usersOrderHistory(user.getId());

      return new ResponseEntity<>(orders,HttpStatus.CREATED);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Order> findOrderById(@PathVariable("id") Long orderId,
                                              @RequestHeader("Authorization") String jwt) throws UserException, OrderException {

       User user = userService.findUserProfileByJwt(jwt);
       Order order = orderService.findOrderById(orderId);
       return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
   }


}