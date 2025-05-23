package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;
import com.ecomweb.online.da.DA.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecomweb.online.da.DA.model.ShoppingCart;

/**
 * Service class for managing shopping cart operations.
 * Provides methods for retrieving and updating the shopping cart.
 */
@Service
public class ShoppingCartService {

    /**
     * The shopping cart instance.
     */
    private final ShoppingCart cart;

    @Autowired
    public ShoppingCartService(PriceCalculationService priceCalculationService) {
        this.cart = new ShoppingCart();
        // Setting the cart's currency to the default (read from config)
        this.cart.setCurrency(priceCalculationService.getDefaultCurrency());
    }

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
     * @param product  the product to add
     * @param quantity the quantity to add
     */
    public void addProductToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }
}
