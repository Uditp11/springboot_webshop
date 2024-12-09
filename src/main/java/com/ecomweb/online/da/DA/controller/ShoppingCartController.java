package com.ecomweb.online.da.DA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomweb.online.da.DA.service.ShoppingCartService;

import java.text.DecimalFormat;
import java.util.Map;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        var cart = cartService.getCart();

        // Fetching products from the ShoppingCart using the getProducts method
        Map<?, ?> products = cart.getProducts();

        // Calculating and formatting the total price
        double totalPrice = cart.calculateTotalPrice();
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedTotalPrice = df.format(totalPrice);

        // Adding data to the model for rendering
        model.addAttribute("products", products);
        model.addAttribute("totalPrice", formattedTotalPrice);

        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable int id) {
        cartService.addProductToCart(id);
        return "redirect:/cart";
    }
}
