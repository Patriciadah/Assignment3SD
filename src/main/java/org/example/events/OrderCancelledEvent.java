package org.example.events;
import org.example.models.Order;
import java.util.Date;
public class OrderCancelledEvent implements Event{
    private final String eventId;
    private final String streamId;
    private final Order order;
    private final Date occurredOn;
    private String description;

    public OrderCancelledEvent(String eventId, String streamId, Order order,String description) {
        this.eventId = eventId;
        this.streamId = streamId;
        this.order = order;
        this.description=description;
        this.occurredOn = new Date();
    }
    @Override
    public Date getOccurredOn() {
        return occurredOn;
    }

    @Override
    public String getType() {
        return "OrderCancelledEvent";
    }

    public String getEventId() {
        return eventId;
    }

    public String getStreamId() {
        return streamId;
    }

    public Order getOrder() {
        return order;
    }


    public String getOrderId() {
        return getOrder().getId();
    }
}
