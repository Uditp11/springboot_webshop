package com.ecomweb.online.da.DA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomweb.online.da.DA.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("totalPrice", cartService.getCart().calculateTotalPrice());
        return "cart";
    }

    @GetMapping("/cart-add/{id}")
    public String addToCart(@PathVariable int id) {
        cartService.addProductToCart(id);
        return "redirect:/cart";
    }
}
