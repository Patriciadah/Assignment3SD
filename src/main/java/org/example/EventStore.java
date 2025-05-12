package org.example;

import org.example.events.Event;

import java.util.*;

public class EventStore {
    private final List<Event> events = new ArrayList<>();

    public void append(Event event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
