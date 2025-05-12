package org.example.projections;

import org.example.events.Event;
import org.example.events.OrderCancelledEvent;
import org.example.events.OrderPlacedEvent;
import org.example.events.TradeExecutedEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderBookProjection {
    private final Map<String, OrderPlacedEvent> activeOrders = new HashMap<>();

    public void apply(Event event) {
        if (event instanceof OrderPlacedEvent) {
            OrderPlacedEvent e = (OrderPlacedEvent) event;
            activeOrders.put(e.getOrderId(), e);
        } else if (event instanceof OrderCancelledEvent) {
            OrderCancelledEvent e = (OrderCancelledEvent) event;
            activeOrders.remove(e.getOrderId());
        } else if (event instanceof TradeExecutedEvent) {
            TradeExecutedEvent e = (TradeExecutedEvent) event;
            activeOrders.remove(e.getBuyOrderId());
            activeOrders.remove(e.getSellOrderId());
        }
    }

    public Collection<OrderPlacedEvent> getActiveOrders() {
        return activeOrders.values();
    }
}
