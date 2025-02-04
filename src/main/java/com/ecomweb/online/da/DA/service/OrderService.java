package com.ecomweb.online.da.DA.service;

import com.ecomweb.online.da.DA.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public Order finalizeOrder(double totalPrice, int userId) {
        // Create a new Order object
        // (In a real app, you'd probably save it to the DB here.)
        return new Order(totalPrice, userId);
    }
}
