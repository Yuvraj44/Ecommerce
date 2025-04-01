package com.example.ECommerce.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ECommerce.Entity.Product;
import com.example.ECommerce.Exception.ProductException;
import com.example.ECommerce.Service.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    
    @GetMapping("/products")
    public ResponseEntity <Page<Product>> findProductByCategoryHandler(@RequestParam(required = false) String category,
                                                                      @RequestParam(required = false) List<String> color,
                                                                      @RequestParam(required = false) List<String> size,
                                                                      @RequestParam(required = false) Integer minPrice,
                                                                      @RequestParam(required = false) Integer maxPrice,
                                                                      @RequestParam(required = false) Integer minDiscount,
                                                                      @RequestParam(required = false) String sort,
                                                                      @RequestParam(required = false) String stock,
                                                                      @RequestParam(required = false) Integer pageNumber,
                                                                      @RequestParam(required = false) Integer pageSize){

        Page<Product> res = productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);

        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

    }


    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {

        Product product = productService.findProductById(productId);

        return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
    }

}
