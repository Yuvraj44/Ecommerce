package com.example.ECommerce.Service;

import java.util.List;

import com.example.ECommerce.Entity.Address;
import com.example.ECommerce.Entity.Order;
import com.example.ECommerce.Entity.User;
import com.example.ECommerce.Exception.OrderException;

public interface OrderService {

	Order createOrder(User user, Address shippingAddress);

	Order findOrderById(Long orderId) throws OrderException;

	List<Order> usersOrderHistory(Long userId);

	Order placedOrder(Long orderId) throws OrderException;

	Order confirmedOrder(Long orderId) throws OrderException;

	Order shippedOrder(Long orderId) throws OrderException;

	Order deliveredOrder(Long orderId) throws OrderException;

	Order cancledOrder(Long orderId) throws OrderException;

	List<Order> getAllOrders();

	void deleteOrder(Long orderId) throws OrderException;
}