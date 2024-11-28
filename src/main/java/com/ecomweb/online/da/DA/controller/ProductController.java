package com.ecomweb.online.da.DA.controller;

import org.springframework.web.bind.annotation.*;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = Arrays.asList(
        new Product(1, "Adidas", "T-Shirt", 29.99, "M", "Black"),
        new Product(2, "Levi", "Jeans", 49.99, "L", "Blue"),
        new Product(3, "The North Face", "Jacket", 89.99, "XL", "Green"),
        new Product(4, "Nike", "Sneakers", 79.99, "42", "White"),
        new Product(5, "Gucci", "Hat", 44.99, "One Size", "Red")
    );

    private ProductService productService;

    // Constructor Injection
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Setter Injection
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/id/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(products, id);
    }


    @GetMapping("/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(products, name);
    }

    @GetMapping("/type/{type}")
    public List<Product> getProductsByType(@PathVariable String type) {
        return productService.getProductsByType(products, type);
    }

    @GetMapping("/size/{size}")
    public List<Product> getProductsBySize(@PathVariable String size) {
        return productService.getProductsBySize(products, size);
    }

    @GetMapping("/color/{color}")
    public List<Product> getProductsByColor(@PathVariable String color) {
        return productService.getProductsByColor(products, color);
    }
}
