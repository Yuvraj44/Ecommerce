package com.example.ECommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ECommerce.Entity.Address;

public interface AddressRepo extends JpaRepository <Address, Long>{
	

}
