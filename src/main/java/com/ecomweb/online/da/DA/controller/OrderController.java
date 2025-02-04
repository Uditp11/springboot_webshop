package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Adapter.OrderAdapter;
import com.ecomweb.online.da.DA.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final OrderAdapter orderAdapter;

    @Autowired
    public OrderController(OrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("totalPrice") double totalPrice, Model model) {
        // Use the adapter to finalize the order (which also sends an e-mail)
        Order order = orderAdapter.finalizeOrder(totalPrice);

        // Add the order to the model so we can display it in the success page
        model.addAttribute("order", order);

        // Return the name of the Thymeleaf template to render
        return "order-success";
    }
}
