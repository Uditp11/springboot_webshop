package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    // Map to store product stock keyed by product ID
    private final Map<Integer, Integer> productStock = new HashMap<>();

    public InventoryService() {
        // Initialize stock for some products
        productStock.put(1, 2);
        productStock.put(2, 10);
        productStock.put(3, 30);
        productStock.put(4, 30);
        productStock.put(5, 30);
        productStock.put(6, 30);
        productStock.put(7, 30);
    }

    // Method to initialize stock for a product
    public void setStockForProduct(int productId, int stock) {
        productStock.put(productId, stock);
    }

    // Method to retrieve stock for a product
    public int getStockForProductId(int productId) {
        return productStock.getOrDefault(productId, 0);
    }

    // Method to reduce stock for a product
    public boolean reduceStockForProductId(int productId) {
        int currentStock = productStock.getOrDefault(productId, 0);
        if (currentStock > 0) {
            productStock.put(productId, currentStock - 1);
            return true;
        }
        return false; // Stock already zero or invalid
    }

    // Optional helper to check stock availability before any operation
    public boolean isStockAvailable(int productId) {
        return productStock.getOrDefault(productId, 0) > 0;
    }

    // Method to remove stock for a product (for cleanup on deletion)
    public void removeStockForProduct(int productId) {
        productStock.remove(productId);
    }
}
