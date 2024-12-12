package com.ecomweb.online.da.DA.controller;

import java.util.Collection;
import java.util.List;

import com.ecomweb.online.da.DA.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.ProductService;

/**
 * Controller class for managing product-related REST API endpoints.
 * Handles CRUD operations for products.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    /**
     * Service for managing product-related operations.
     */
    @Autowired
    private ProductService productService;

    /**
     * Service for managing inventory-related operations.
     */
    @Autowired
    private InventoryService inventoryService;

    // ===== REST API Endpoints =====

    /**
     * Retrieves all available products.
     *
     * @return A collection of all products.
     */
    @GetMapping
    @ResponseBody
    public Collection<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Adds a new product to the database.
     *
     * @param product The product to be added.
     * @return ResponseEntity containing the added product.
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.ok(addedProduct);
    }

    /**
     * Updates an existing product.
     *
     * @param product The updated product details.
     * @return ResponseEntity containing the updated product.
     */
    @PutMapping
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Retrieves products by their name.
     *
     * @param name The name of the product to search.
     * @return A list of products matching the specified name.
     */
    @GetMapping("/name/{name}")
    @ResponseBody
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    /**
     * Retrieves products by their type.
     *
     * @param type The type of the product to search.
     * @return A list of products matching the specified type.
     */
    @GetMapping("/type/{type}")
    @ResponseBody
    public List<Product> getProductsByType(@PathVariable String type) {
        return productService.getProductsByType(type);
    }

    /**
     * Retrieves products by their size.
     *
     * @param size The size of the product to search.
     * @return A list of products matching the specified size.
     */
    @GetMapping("/size/{size}")
    @ResponseBody
    public List<Product> getProductsBySize(@PathVariable String size) {
        return productService.getProductsBySize(size);
    }

    /**
     * Retrieves products by their color.
     *
     * @param color The color of the product to search.
     * @return A list of products matching the specified color.
     */
    @GetMapping("/color/{color}")
    @ResponseBody
    public List<Product> getProductsByColor(@PathVariable String color) {
        return productService.getProductsByColor(color);
    }
}
