package it.unibo.makeanicecream.api;

public interface Event {
    EventType getType ();

    String getData();
}
