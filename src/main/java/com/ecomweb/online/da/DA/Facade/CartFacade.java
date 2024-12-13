package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Product;
import com.ecomweb.online.da.DA.service.InventoryService;
import com.ecomweb.online.da.DA.service.PriceCalculationService;
import com.ecomweb.online.da.DA.service.ProductService;
import com.ecomweb.online.da.DA.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Facade class for managing shopping cart operations.
 * Combines shopping cart logic, product management, and inventory handling.
 */
@Component
public class CartFacade {

    /**
     * Service for managing shopping cart operations.
     */
    @Autowired
    private ShoppingCartService cartService;

    /**
     * Service for managing product-related operations.
     */
    @Autowired
    private ProductService productService;

    /**
     * Service for managing inventory-related operations.
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * Service for calculating prices and handling currency conversions.
     */
    @Autowired
    private PriceCalculationService priceCalculationService;

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
        BigDecimal totalPrice = cartService.getCart().calculateTotalPrice();
        BigDecimal roundedTotal = priceCalculationService.roundPrice(totalPrice);
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(roundedTotal);
    }
}
