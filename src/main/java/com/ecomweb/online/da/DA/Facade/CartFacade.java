package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.ProductService;
import com.ecomweb.online.da.DA.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Map;

@Component
public class CartFacade {

    @Autowired
    private ShoppingCartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    /**
     * Adds a product to the shopping cart after validating stock.
     * Also updates inventory stock if successful.
     *
     * @param productId the ID of the product to add
     * @return true if the product was added successfully, false if out of stock or invalid
     */
    public boolean addProductToCart(long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            int currentStock = inventoryService.getStockForProductId(productId);
            if (currentStock > 0) {
                inventoryService.setStockForProduct(productId, currentStock - 1);
                cartService.addProductToCart(product, 1);
                return true;
            }
        }
        return false; // Product not added due to invalid ID or insufficient stock
    }

    /**
     * Retrieves the current shopping cart products and their quantities.
     *
     * @return map of Product to quantity
     */
    public Map<Product, Integer> getCartProducts() {
        return cartService.getCart().getProducts();
    }

    /**
     * Calculates the total price of the shopping cart.
     *
     * @return formatted total price as a String
     */
    public String getFormattedTotalPrice() {
        double totalPrice = cartService.getCart().calculateTotalPrice();
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(totalPrice);
    }
}
