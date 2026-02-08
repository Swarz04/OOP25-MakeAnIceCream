package it.unibo.makeanicecream.view;

import javax.swing.event.DocumentEvent.EventType;

public class EventImpl {
    private final EventType type;
    private final String data;

    public EventImpl(final EventType type, final String name) {
        this.type = type;
        this.data = name;
    }

    public EventType getType(){
        return this.type;
    }

    public String getData(){
        return this.data;
    }
}
