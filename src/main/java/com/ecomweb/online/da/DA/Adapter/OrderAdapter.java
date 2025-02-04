package com.ecomweb.online.da.DA.Adapter;

import com.ecomweb.online.da.DA.Facade.OrderFacade;
import com.ecomweb.online.da.DA.model.Order;
import com.ecomweb.online.da.DA.service.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter {

    private final OrderFacade orderFacade;
    private final EMailService eMailService;

    @Autowired
    public OrderAdapter(OrderFacade orderFacade, EMailService eMailService) {
        this.orderFacade = orderFacade;
        this.eMailService = eMailService;
    }

    /**
     * Finalizes the order and then simulates sending an e-mail.
     *
     * @param totalPrice The total price of the order.
     * @return The finalized Order.
     */
    public Order finalizeOrder(double totalPrice) {
        // Finalize the order using the existing facade.
        Order order = orderFacade.finalizeOrder(totalPrice);

        // Assume the Order model contains the userId (retrieved by OrderFacade).
        int userId = order.getUserId();

        // Call the e-mail service to simulate sending an e-mail.
        eMailService.sendEMail(userId);

        return order;
    }
}
