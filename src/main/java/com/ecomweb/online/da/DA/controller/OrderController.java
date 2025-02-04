package com.ecomweb.online.da.DA.controller;

import com.ecomweb.online.da.DA.Facade.OrderFacade;
import com.ecomweb.online.da.DA.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final OrderFacade orderFacade;

    @Autowired
    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam("totalPrice") double totalPrice, Model model) {
        // Use the facade to finalize the order
        Order order = orderFacade.finalizeOrder(totalPrice);

        // Add the order to the model so we can display it in the success page
        model.addAttribute("order", order);

        // Return the name of the Thymeleaf template to render
        return "order-success";
    }
}
