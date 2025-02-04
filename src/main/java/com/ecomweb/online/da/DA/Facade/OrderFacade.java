package com.ecomweb.online.da.DA.Facade;

import com.ecomweb.online.da.DA.model.Order;
import com.ecomweb.online.da.DA.service.OrderService;
import com.ecomweb.online.da.DA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFacade {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderFacade(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public Order finalizeOrder(double totalPrice) {
        // Fetch userId from UserService
        int userId = userService.getUserId();
        // Delegate to OrderService
        return orderService.finalizeOrder(totalPrice, userId);
    }
}
