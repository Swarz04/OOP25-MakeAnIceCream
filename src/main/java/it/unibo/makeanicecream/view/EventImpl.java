package it.unibo.makeanicecream.view;

import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.EventType;

public class EventImpl implements Event {
    private final EventType type;
    private final String data;

    public EventImpl(final EventType type, final String name) {
        this.type = type;
        this.data = name;
    }

    @Override
    public EventType getType() {
        return this.type;
    }

    @Override
    public String getData() {
        return this.data;
    }
}