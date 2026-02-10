package it.unibo.makeanicecream.view;

import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.EventType;

/**
 * Implementation of the {@link Event} interface.
 */
public final class EventImpl implements Event {

    private final EventType type;
    private final String data;

    /**
     * Constructs a new event with the specified type and associated data.
     *
     * @param type the type of the event
     * @param data the data associated with the event (e.g., ingredient name or level number)
     */
    public EventImpl(final EventType type, final String data) {
        this.type = type;
        this.data = data;
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
