package com.example.ECommerce.DTO;

import java.util.HashSet;
import java.util.Set;

import com.example.ECommerce.Entity.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductReq {

	private String title;
	private String description;
	private int price;
	private int discountedPrice;
	private int discountPersent;
	private int quantity;
	private String brand;
	private String color;

	private Set<Size> size = new HashSet<>();

	private String imageUrl;

	private String topLevelCategory;
	private String secondLevelCategory;
	private String thirdLevelCategory;
}
