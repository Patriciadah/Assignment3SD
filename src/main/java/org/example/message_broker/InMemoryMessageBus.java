package org.example.message_broker;

import org.example.events.Event;

import java.util.*;
import java.util.function.Consumer;

public class InMemoryMessageBus implements MessageBus {
    private final Map<String, List<Consumer<Event>>> handlers = new HashMap<>();

    public void publish(Event event) {
        List<Consumer<Event>> eventHandlers = handlers.getOrDefault(event.getType(), List.of());
        for (Consumer<Event> handler : eventHandlers) {
            handler.accept(event); // Simple sync call
        }
    }

    public void subscribe(String eventType, Consumer<Event> handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(handler);
    }
}

