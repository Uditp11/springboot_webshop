package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for managing product inventory.
 * Provides methods for setting, retrieving, and updating stock levels.
 */
@Service
public class InventoryService {

    /**
     * Map storing product stock, keyed by product ID.
     */
    private final Map<Long, Integer> productStock = new HashMap<>();

    /**
     * Initializes the InventoryService with predefined stock values.
     */
    public InventoryService() {
        productStock.put(1L, 2);
        productStock.put(2L, 10);
        productStock.put(3L, 30);
        productStock.put(4L, 30);
        productStock.put(5L, 30);
        productStock.put(6L, 30);
        productStock.put(7L, 30);
    }

    /**
     * Sets the stock for a specific product.
     *
     * @param productId The product's unique identifier.
     * @param stock     The stock quantity to set.
     */
    public void setStockForProduct(long productId, int stock) {
        productStock.put(productId, stock);
    }

    /**
     * Retrieves the stock level for a specific product.
     *
     * @param productId The product's unique identifier.
     * @return The current stock quantity, or 0 if the product is not found.
     */
    public int getStockForProductId(long productId) {
        return productStock.getOrDefault(productId, 0);
    }

    /**
     * Reduces the stock level for a specific product by 1, if available.
     *
     * @param productId The product's unique identifier.
     * @return true if the stock was successfully reduced, false if the stock is 0 or the product is not found.
     */
    public boolean reduceStockForProductId(long productId) {
        int currentStock = productStock.getOrDefault(productId, 0);
        if (currentStock > 0) {
            productStock.put(productId, currentStock - 1);
            return true;
        }
        return false;
    }

    /**
     * Checks if stock is available for a specific product.
     *
     * @param productId The product's unique identifier.
     * @return true if stock is available, false otherwise.
     */
    public boolean isStockAvailable(long productId) {
        return productStock.getOrDefault(productId, 0) > 0;
    }

    /**
     * Removes stock information for a specific product.
     * Typically used during product deletion.
     *
     * @param productId The product's unique identifier.
     */
    public void removeStockForProduct(long productId) {
        productStock.remove(productId);
    }
}
