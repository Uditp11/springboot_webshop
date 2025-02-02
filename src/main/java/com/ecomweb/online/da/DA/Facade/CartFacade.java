package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Currency;
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
     * Overloaded method to get the total price in the given currency, optionally applying discount.
     *
     * @param currency     The currency to convert into (e.g., EURO, DOLLAR).
     * @param applyDiscount If true, apply 10% discount.
     * @return The formatted total price as a String, e.g. "15.99".
     */
    public String getFormattedTotalPrice(Currency currency, boolean applyDiscount) {
        BigDecimal totalPrice = cartService.getCart().calculateTotalPrice();

        // 1) Convert the total from your default currency (assumed EURO) to the chosen currency
        BigDecimal convertedPrice = priceCalculationService.convertCurrency(totalPrice, Currency.EURO, currency);

        // 2) Optionally apply discount
        if (applyDiscount) {
            convertedPrice = priceCalculationService.applyPercentageVoucher(convertedPrice);
        }

        // 3) Round and format to 2 decimals
        BigDecimal finalPrice = priceCalculationService.roundPrice(convertedPrice);
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(finalPrice);
    }
}
