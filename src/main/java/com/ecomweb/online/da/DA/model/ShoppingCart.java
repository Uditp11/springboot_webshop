package com.ecomweb.online.da.DA.model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // Making the products field private for better encapsulation
    private Map<Product, Integer> products = new HashMap<>();

    // Method to add a product to the cart
    public void addProduct(Product product, int quantity) {

        // Update the quantity if the product already exists, otherwise add it
        products.put(product, products.getOrDefault(product, 0) + quantity);

        // Print each product's ID and its quantity
        for (Product p : products.keySet()) {
            System.out.println("Product ID: " + p.getId() + ", Quantity: " + products.get(p));
        }
    }


    // Method to calculate the total price of products in the cart
    public double calculateTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    // Getter for the products map
    public Map<Product, Integer> getProducts() {
        return products;
    }
}
