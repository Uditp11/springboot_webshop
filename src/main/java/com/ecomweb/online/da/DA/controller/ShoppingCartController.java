package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for managing the shopping cart.
 * Handles operations such as viewing and updating the cart.
 */
@Controller
public class ShoppingCartController {

    /**
     * Facade for managing shopping cart operations.
     */
    @Autowired
    private CartFacade cartFacade;

    /**
     * Displays the shopping cart with its products and total price.
     *
     * @param model Spring model object for passing data to the view.
     * @return The Thymeleaf template name for the cart page.
     */
    @GetMapping("/cart")
    public String viewCart(
            @RequestParam(name = "currency", required = false, defaultValue = "EURO") String currencyParam,
            @RequestParam(name = "discount", required = false, defaultValue = "false") boolean applyDiscount,
            Model model)
    {
        // 1. Fetch products
        model.addAttribute("products", cartFacade.getCartProducts());

        // 2. Parse currency
        //    (Handle errors in case someone passes an invalid currency)
        com.ecomweb.online.da.DA.model.Currency currency;
        try {
            currency = com.ecomweb.online.da.DA.model.Currency.valueOf(currencyParam.toUpperCase());
        } catch (IllegalArgumentException e) {
            currency = com.ecomweb.online.da.DA.model.Currency.EURO; // default fallback
        }

        // 3. Calculate the total price in the selected currency, optionally with discount
        //    This is a new method in your facade (see below).
        String totalPrice = cartFacade.getFormattedTotalPrice(currency, applyDiscount);

        // 4. Pass the calculated total, plus the user’s choices, back to the view
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("currency", currency);
        model.addAttribute("applyDiscount", applyDiscount);

        return "cart";
    }


    /**
     * Adds a product to the shopping cart.
     * If the product is out of stock, an error message is displayed.
     *
     * @param id    The ID of the product to be added to the cart.
     * @param model Spring model object for passing data to the view.
     * @return A redirect to the cart page.
     */
    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable int id, Model model) {
        // Add product to the cart and handle out-of-stock scenario
        boolean added = cartFacade.addProductToCart(id);
        if (!added) {
            model.addAttribute("error", "Product is out of stock!");
        }
        return "redirect:/cart";
    }
}
