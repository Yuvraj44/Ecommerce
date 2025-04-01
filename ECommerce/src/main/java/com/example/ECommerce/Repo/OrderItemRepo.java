package com.example.ECommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ECommerce.Entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{

}
