package com.example.ECommerce.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ECommerce.Entity.Address;
import com.example.ECommerce.Entity.Cart;
import com.example.ECommerce.Entity.CartItem;
import com.example.ECommerce.Entity.Order;
import com.example.ECommerce.Entity.OrderItem;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.OrderException;
import com.example.ECommerce.Repo.AddressRepo;
import com.example.ECommerce.Repo.CartRepo;
import com.example.ECommerce.Repo.OrderItemRepo;
import com.example.ECommerce.Repo.OrderRepo;
import com.example.ECommerce.Repo.UserRepo;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.OrderItemService;
import com.example.ECommerce.Service.OrderService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderImpl implements OrderService{

    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final AddressRepo addressRepo;
    private final OrderItemService orderItemService;
    private final OrderItemRepo orderItemRepo;
    private final UserRepo userRepo;

    @Override
    public Order createOrder(User user, Address shipAddress) {
    	
    	shipAddress.setUser(user);
        Address address = addressRepo.save(shipAddress);
        user.getAddress().add(address);
        userRepo.save(user);

        
        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        // Iterating through cart items and creating order items
        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createdOrderItem = orderItemRepo.save(orderItem);
            
            orderItems.add(createdOrderItem);
        }

        // Creating and setting up the order
        Order createdOrder = new Order();
        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotalPrice());
        createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createdOrder.setDiscount(cart.getDiscount());
        createdOrder.setTotalItem(cart.getTotalItem());

        createdOrder.setShippingAddress(address);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.getPaymentDetails().setStatus("PENDING");
        createdOrder.setCreatedAt(LocalDateTime.now());

        
        Order savedOrder = orderRepo.save(createdOrder);
    	
    	for(OrderItem item: orderItems)
    	{
    		item.setOrder(savedOrder);
    		orderItemRepo.save(item);
    	}
        return savedOrder;
    }

    @Override
    public Order findOrderById(Long orderId) throws OrderException {
        Optional <Order> opt= orderRepo.findById(orderId);
        
        if(opt.isPresent())
        	return opt.get();
        
        throw new OrderException ("Order doesn't exist");
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
    	List <Order> orders=orderRepo.getUserOrders(userId);
        return orders;
    }

    @Override
    public Order placedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancledOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
    	Order order=findOrderById(orderId);
    	orderRepo.deleteById(orderId);
    }
}