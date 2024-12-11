package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    // Map to store product stock keyed by product ID
    private final Map<Long, Integer> productStock = new HashMap<>();

    public InventoryService() {
        // Initialize stock for some products
        productStock.put(1L, 2);
        productStock.put(2L, 10);
        productStock.put(3L, 30);
        productStock.put(4L, 30);
        productStock.put(5L, 30);
        productStock.put(6L, 30);
        productStock.put(7L, 30);
    }

    // Method to initialize stock for a product
    public void setStockForProduct(long productId, int stock) {
        productStock.put( productId, stock);
    }

    // Method to retrieve stock for a product
    public int getStockForProductId(long productId) {
        return productStock.getOrDefault(productId, 0);
    }

    // Method to reduce stock for a product
    public boolean reduceStockForProductId(long productId) {
        int currentStock = productStock.getOrDefault(productId, 0);
        if (currentStock > 0) {
            productStock.put( productId, currentStock - 1);
            return true;
        }
        return false; // Stock already zero or invalid
    }

    // Optional helper to check stock availability before any operation
    public boolean isStockAvailable(long productId) {
        return productStock.getOrDefault(productId, 0) > 0;
    }

    // Method to remove stock for a product (for cleanup on deletion)
    public void removeStockForProduct(long productId) {
        productStock.remove(productId);
    }
}
