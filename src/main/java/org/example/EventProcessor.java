package org.example;

import org.example.events.Event;
import org.example.projections.AccountBalanceProjection;
import org.example.projections.OrderBookProjection;

public class EventProcessor {
    private final OrderBookProjection orderBookProjection;
    private final AccountBalanceProjection accountBalanceProjection;

    public EventProcessor(OrderBookProjection orderBookProjection, AccountBalanceProjection accountBalanceProjection) {
        this.orderBookProjection = orderBookProjection;
        this.accountBalanceProjection = accountBalanceProjection;
    }

    public void process(Event event) {
        orderBookProjection.apply(event);
        accountBalanceProjection.apply(event);
    }
}
