package org.example.events;

import org.example.models.Trade; // Assuming you might relate it to a Trade
import java.util.Date;

public class FundsDebitedEvent implements Event {
    private final String eventId;
    private final String streamId;// Account ID
    private final float debitedAmount;
    private final Trade trade; // You might want to associate it with a Trade or another entity
    private final Date occurredOn;
    private String reason; // Optional: Reason for the debit

    public FundsDebitedEvent(String eventId, String streamId, float debitedAmount, Trade trade, String reason) {
        this.eventId = eventId;
        this.streamId = streamId;
        this.debitedAmount = debitedAmount;
        this.trade = trade;
        this.occurredOn = new Date();
        this.reason = reason;
    }


    public FundsDebitedEvent(String eventId, String streamId, float debitedAmount, String reason) {
        this(eventId, streamId, debitedAmount, null, reason);
    }


    public FundsDebitedEvent(String eventId, String streamId, float debitedAmount) {
        this(eventId, streamId, debitedAmount, null, null);
    }

    @Override
    public Date getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getType() {
        return "FundsDebitedEvent";
    }

    public String getEventId() {
        return eventId;
    }

    public String getStreamId() {
        return streamId;
    }

    public float getDebitedAmount() {
        return debitedAmount;
    }

    public Trade getTransaction() {
        return trade;
    }

    public String getReason() {
        return reason;
    }
    public String getAccountId(){return streamId;}
}