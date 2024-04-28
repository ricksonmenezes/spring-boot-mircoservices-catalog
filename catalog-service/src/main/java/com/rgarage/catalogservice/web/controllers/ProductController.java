package com.rgarage.catalogservice.web.controllers;

import com.rgarage.catalogservice.domain.PagedResult;
import com.rgarage.catalogservice.domain.Product;
import com.rgarage.catalogservice.domain.ProductNotFoundException;
import com.rgarage.catalogservice.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {

        return productService.getProducts(pageNo);
    }

    @GetMapping(value = "/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {

        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
