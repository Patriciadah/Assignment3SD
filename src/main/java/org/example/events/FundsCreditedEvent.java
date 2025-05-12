package org.example.events;


import org.example.models.Trade;

import java.util.Date;

public class FundsCreditedEvent implements Event {
    private final String eventId;
    private final String streamId; // Account id
    private final float creditedAmount;
    private final Trade trade;
    private final Date occurredOn;

    public FundsCreditedEvent(String eventId, String streamId, float creditedAmount, Trade trade) {
        this.eventId = eventId;
        this.streamId = streamId;
        this.creditedAmount = creditedAmount;
        this.trade = trade;
        this.occurredOn = new Date();
    }

    // Overloaded constructor if you don't have a transaction ID initially
    public FundsCreditedEvent(String eventId, String streamId, float creditedAmount) {
        this(eventId, streamId, creditedAmount, null);
    }

    @Override
    public Date getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getType() {
        return "FundsCreditedEvent";
    }

    public String getEventId() {
        return eventId;
    }

    public String getStreamId() {
        return streamId;
    }

    public float getCreditedAmount() {
        return creditedAmount;
    }
    public String getAccountId(){return streamId;}

    public Trade getTransaction() {
        return trade;
    }
}