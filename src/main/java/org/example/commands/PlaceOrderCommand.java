package org.example.commands;

import org.example.models.Order;

public class PlaceOrderCommand implements Command {
    private final Order order;
    private final String side;
    private final String accountId;

    public PlaceOrderCommand(Order order, String side, String accountId) {
        this.order = order;
        this.side = side;
        this.accountId = accountId;
    }

    public Order getOrder() {
        return order;
    }


    public String getSide() {
        return side;
    }

    public String getAccountId() {
        return accountId;
    }
}
