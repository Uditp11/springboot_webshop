package com.ecomweb.online.da.DA.service;

import com.ecomweb.online.da.DA.model.Order;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    // Original method (remains working as before)
    public Order finalizeOrder(double totalPrice, int userId) {
        // In a real app, you might save the order to the database here.
        return new Order(totalPrice, userId);
    }

    /*
     * ================================
     * TESTING: Circular Dependency Code
     * Uncomment the code below to test the circular dependency error.
     * This code adds a dependency on UserService and new methods.
     *
     * When these are active, Spring will throw a circular dependency error:
     *  - OrderService depends on UserService (via autowiring)
     *  - UserService (see below) depends on OrderService
     *
     * After testing, I commented them out to let the app start normally.
     * ================================
     */

    // @Autowired
    // private UserService userService;
    //
    // // New method that finalizes an order using a BigDecimal and fetches userId from UserService.
    // public Order finalizeOrderWithTotal(BigDecimal total) {
    //     int userId = userService.getUserId();
    //     return new Order(total.doubleValue(), userId);
    // }
    //
    // // New method that returns a recent order for a given user.
    // public Order getRecentOrderForUser(String userId) {
    //     // For simplicity, returning an Order with a total of zero.
    //     return new Order(0.0, Integer.parseInt(userId));
    // }
}
