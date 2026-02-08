package it.unibo.makeanicecream.view;

import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.EventType;

/**
 * Implementation of the {@link Event} interface.
 */
public class EventImpl implements Event {
    private final EventType type;
    private final String data;

    /**
     * Builds a new EventImpl.
     *
     * @param type the type of the event
     * @param name the data associated with the event
     */
    public EventImpl(final EventType type, final String name) {
        this.type = type;
        this.data = name;
    }

    /** {@inheritDoc} */
    @Override
    public EventType getType() {
        return this.type;
    }

    /** {@inheritDoc} */
    @Override
    public String getData() {
        return this.data;
    }
}
