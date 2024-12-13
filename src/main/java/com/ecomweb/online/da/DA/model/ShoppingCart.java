package com.ecomweb.online.da.DA.model;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

/**
 * Represents a shopping cart containing products and their quantities.
 * Provides methods to manage products and calculate the total price.
 */
public class ShoppingCart {

    /**
     * Map holding products and their respective quantities.
     */
    private Map<Product, Integer> products = new HashMap<>();

    /**
     * Adds a product to the shopping cart.
     * Updates the quantity if the product already exists.
     *
     * @param product  The product to be added.
     * @param quantity The quantity of the product.
     */
    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    /**
     * Calculates the total price of all products in the cart.
     *
     * @return The total price as a double.
     */
    public BigDecimal calculateTotalPrice() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Retrieves the products and their quantities in the cart.
     *
     * @return A map of products and their quantities.
     */
    public Map<Product, Integer> getProducts() {
        return products;
    }
}
