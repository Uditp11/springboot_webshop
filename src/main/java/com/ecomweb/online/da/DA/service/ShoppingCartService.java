package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.model.ShoppingCart;

@Service
public class ShoppingCartService {

    private final ShoppingCart cart = new ShoppingCart();

    /**
     * Returns the current shopping cart.
     *
     * @return the ShoppingCart object
     */
    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Adds a product to the cart without validating stock.
     * Validation should be handled by a higher-level component like the facade.
     *
     * @param product the product to add
     * @param quantity the quantity to add
     */
    public void addProductToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }
}
