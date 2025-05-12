package org.example.events;


import org.example.models.Trade;

import java.util.Date;

public class TradeExecutedEvent implements Event{
    private final String eventId;
    private final String streamId;
    private final Trade trade;
    private final Date occurredOn;

    public TradeExecutedEvent(String eventId, String streamId, Trade trade) {
        this.eventId = eventId;
        this.streamId = streamId;
        this.trade = trade;
        this.occurredOn = new Date();
    }
    @Override
    public Date getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getType() {
        return "TradeExecutedEvent";
    }

    public String getBuyOrderId() {
        return trade.getBuyOrder().getId();
    }
    public String getSellOrderId() {
        return trade.getSellOrder().getId();
    }
}
