package com.ecomweb.online.da.DA.controller;

import java.util.List;

import com.ecomweb.online.da.DA.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ===== REST API Endpoints =====

    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return ResponseEntity.ok(addedProduct);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(404).body(null); // Return 404 for not found
        }
        // Attach stock value
        product.setStock(inventoryService.getStockForProductId(product.getId()));
        return ResponseEntity.ok(product); // Return enriched product with stock
    }


    @GetMapping("/name/{name}")
    @ResponseBody
    public List<Product> getProductsByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

    @GetMapping("/type/{type}")
    @ResponseBody
    public List<Product> getProductsByType(@PathVariable String type) {
        return productService.getProductsByType(type);
    }

    @GetMapping("/size/{size}")
    @ResponseBody
    public List<Product> getProductsBySize(@PathVariable String size) {
        return productService.getProductsBySize(size);
    }

    @GetMapping("/color/{color}")
    @ResponseBody
    public List<Product> getProductsByColor(@PathVariable String color) {
        return productService.getProductsByColor(color);
    }

    // ===== Inventory Management Endpoints =====
//
//    @PostMapping("/{id}/set-stock")
//    @ResponseBody
//    public ResponseEntity<String> setProductStock(@PathVariable int id, @RequestParam int stock) {
//        boolean success = productService.setProductStock(id, stock);
//        if (!success) {
//            return ResponseEntity.badRequest().body("Product not found");
//        }
//        return ResponseEntity.ok("Stock initialized for product with ID: " + id);
//    }
//
//    @GetMapping("/{id}/stock")
//    @ResponseBody
//    public ResponseEntity<Integer> getProductStock(@PathVariable int id) {
//        int stock = productService.getProductStock(id);
//        return ResponseEntity.ok(stock);
//    }
//
//    @PostMapping("/{id}/reduce-stock")
//    @ResponseBody
//    public ResponseEntity<String> reduceProductStock(@PathVariable int id) {
//        boolean success = productService.reduceProductStock(id);
//        if (!success) {
//            return ResponseEntity.badRequest().body("Stock for product with ID: " + id + " is already zero.");
//        }
//        return ResponseEntity.ok("Stock reduced for product with ID: " + id);
//    }
//
//    @PostMapping("/{id}/increase-stock")
//    @ResponseBody
//    public ResponseEntity<String> increaseProductStock(@PathVariable int id, @RequestParam int amount) {
//        if (amount <= 0) {
//            return ResponseEntity.badRequest().body("Invalid stock increment value");
//        }
//        boolean success = productService.increaseProductStock(id, amount);
//        if (!success) {
//            return ResponseEntity.badRequest().body("Product not found");
//        }
//        return ResponseEntity.ok("Stock increased for product with ID: " + id);
//    }
}