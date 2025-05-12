package org.example.message_broker;

import org.example.events.Event;

import java.util.function.Consumer;

public interface MessageBus {
    void publish(Event event);
    void subscribe(String eventType, Consumer<Event> handler);
}
