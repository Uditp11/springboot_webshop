package com.ecomweb.online.da.DA.model;

import java.util.HashMap;
import java.util.Map;

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

        // Print product details for debugging
        for (Product p : products.keySet()) {
            System.out.println("Product ID: " + p.getId() + ", Quantity: " + products.get(p));
        }
    }

    /**
     * Calculates the total price of all products in the cart.
     *
     * @return The total price as a double.
     */
    public double calculateTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
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
