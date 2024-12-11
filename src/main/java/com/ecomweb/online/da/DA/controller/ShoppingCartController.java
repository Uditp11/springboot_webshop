package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShoppingCartController {

    @Autowired
    private CartFacade cartFacade;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        // Fetch products and total price using the facade
        model.addAttribute("products", cartFacade.getCartProducts());
        model.addAttribute("totalPrice", cartFacade.getFormattedTotalPrice());
        return "cart";
    }

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
