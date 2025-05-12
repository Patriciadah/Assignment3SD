package org.example.commands;

import org.example.models.Order;

public class CancelOrderCommand implements Command {
    private final String streamId;
    private final Order order;
    private final String description;

    public CancelOrderCommand(String streamId, Order order, String description) {

        this.streamId = streamId;
        this.order = order;
        this.description = description;
    }


    public String getAccountId() {
        return streamId;
    }

    public Order getOrder() {
        return order;
    }

    public String getReason() {
        return description;
    }
}
