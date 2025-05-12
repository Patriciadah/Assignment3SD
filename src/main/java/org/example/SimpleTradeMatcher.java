package org.example;

import org.example.events.OrderPlacedEvent;
import org.example.models.Trade;
import org.example.projections.OrderBookProjection;

import java.util.List;

public class SimpleTradeMatcher {
    private final OrderBookProjection orderBook;

    public SimpleTradeMatcher(OrderBookProjection orderBook) {
        this.orderBook = orderBook;
    }

    public Trade tryMatch() {
        List<OrderPlacedEvent> buys = orderBook.getActiveOrders().stream()
                .filter(e -> e.getSide().equals("BUY"))
                .toList();

        List<OrderPlacedEvent> sells = orderBook.getActiveOrders().stream()
                .filter(e -> e.getSide().equals("SELL"))
                .toList();

        for (var buy : buys) {
            for (var sell : sells) {
                if (buy.getOrder().getAmount() == sell.getOrder().getAmount()) {
                    return new Trade(buy.getOrder(), sell.getOrder());
                }
            }
        }
        return null;
    }
}
