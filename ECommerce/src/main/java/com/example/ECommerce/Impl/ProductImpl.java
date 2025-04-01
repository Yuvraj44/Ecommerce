package com.example.ECommerce.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ECommerce.DTO.ProductReq;
import com.example.ECommerce.Entity.Category;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Repo.CategoryRepo;
import com.example.ECommerce.Repo.ProductRepo;
import com.example.ECommerce.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductImpl implements ProductService {

	private final CategoryRepo categoryRepoobj;
	private final ProductRepo productRepoobj;

	@Override
	public Product createProduct(ProductReq req) {
		Category topLevel = categoryRepoobj.findByName(req.getTopLevelCategory());

		if (topLevel == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			topLevel = categoryRepoobj.save(topLevelCategory);
		}

		Category secondLevel = categoryRepoobj.findByNameAndParent(req.getSecondLevelCategory(), topLevel.getName());
		if (secondLevel == null) {
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(req.getSecondLevelCategory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);
			secondLevel = categoryRepoobj.save(secondLevelCategory);
		}

		Category thirdLevel = categoryRepoobj.findByNameAndParent(req.getThirdLevelCategory(), secondLevel.getName());
		if (thirdLevel == null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getThirdLevelCategory());
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevelCategory.setLevel(3);
			thirdLevel = categoryRepoobj.save(thirdLevelCategory);
		}

		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSize());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());

		Product savedProduct = productRepoobj.save(product);

		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepoobj.delete(product);
		return "Product Deleted Successfully";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product = findProductById(productId);

		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}
		return productRepoobj.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> opt = productRepoobj.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product not found with id - " + id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
	                                   Integer maxPrice, Integer minDiscount, String sort, String stock, 
	                                   Integer pageNumber, Integer pageSize) {
	    
	    // Set default values for pageNumber and pageSize if they are null
	    if (pageNumber == null) {
	        pageNumber = 0; // default page number
	    }
	    if (pageSize == null) {
	        pageSize = 10; // default page size
	    }

	    Pageable pageable = PageRequest.of(pageNumber, pageSize);

	    // Handle optional parameters with default values or skip if null
	    Integer minPriceValue = (minPrice != null) ? minPrice : 0;
	    Integer maxPriceValue = (maxPrice != null) ? maxPrice : Integer.MAX_VALUE;
	    Integer minDiscountValue = (minDiscount != null) ? minDiscount : 0;

	    // Fetch filtered products using repository's query
	    Page<Product> filteredProducts = productRepoobj.filterProducts(category, minPriceValue, maxPriceValue, minDiscountValue, sort, pageable);

	    // Further filter the results in memory for colors and stock
	    List<Product> filteredList = filteredProducts.getContent();

	    // Filter by colors if provided
	    if (colors != null && !colors.isEmpty()) {
	        filteredList = filteredList.stream()
	                                   .filter(p -> colors.stream()
	                                                      .anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
	                                   .collect(Collectors.toList());
	    }

	    // Filter by stock if provided
	    if (stock != null) {
	        if (stock.equals("in_stock")) {
	            filteredList = filteredList.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
	        } else if (stock.equals("out_of_stock")) {
	            filteredList = filteredList.stream().filter(p -> p.getQuantity() <= 0).collect(Collectors.toList());
	        }
	    }

	    return new PageImpl<>(filteredList, pageable, filteredList.size());
	}


	@Override
	public List<Product> viewAllProducts() {
		return productRepoobj.findAll();
	}
}
