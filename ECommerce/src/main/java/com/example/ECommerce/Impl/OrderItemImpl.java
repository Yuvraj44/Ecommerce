package com.example.ECommerce.Impl;

import org.springframework.stereotype.Service;

import com.example.ECommerce.Entity.OrderItem;
import com.example.ECommerce.Repo.OrderItemRepo;
import com.example.ECommerce.Service.OrderItemService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderItemImpl implements OrderItemService{
	
	private final OrderItemRepo orderItemRepo;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepo.save(orderItem);
	}

}
