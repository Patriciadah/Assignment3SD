package org.example.events;

import org.example.models.Order;

import java.util.Date;

public class OrderPlacedEvent implements Event {
    private final String eventId;
    private final String streamId;
    private final String side;
    private final Order order;
    private final Date occurredOn;


    public OrderPlacedEvent(Order order,String eventId,String side, String streamId) {
        this.eventId=eventId;
        this.streamId=streamId;
        this.order=order;
        this.side=side;
        this.occurredOn = new Date();
    }

    @Override
    public Date getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getType() {
        String type = "OrderPlacedEvent";
        return type;
    }

    public String getEventId() {
        return eventId;
    }

    public String getOrderBookId() {
        return streamId;
    }

    public Order getOrder() {
        return order;
    }

    public String getOrderId() {
        return order.getId();
    }

    public String getSide() {
        return side;
    }
}
