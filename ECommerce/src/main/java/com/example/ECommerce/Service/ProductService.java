package com.example.ECommerce.Service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.example.ECommerce.DTO.ProductReq;
import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Exception.ProductException;

public interface ProductService {

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long id) throws ProductException;

    List<Product> findProductByCategory(String category);
    
    Page<Product> getAllProduct(String category,
    							List<String> colors,
    							List<String> sizes,
                                Integer minPrice,
                                Integer maxPrice,
                                Integer minDiscount,
                                String sort,
                                String stock,
                                Integer pageNumber,
                                Integer pageSize);

	Product createProduct(ProductReq product);

	List<Product> viewAllProducts();

	
}
