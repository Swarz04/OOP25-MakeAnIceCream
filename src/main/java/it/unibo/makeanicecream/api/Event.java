package it.unibo.makeanicecream.api;

/**
 * Represents an action performed by the user in the game.
 */
public interface Event {

    /**
     * Returns the type of the user action.
     *
     * @return the type of this action
     */
    EventType getType();

    /**
     * Returns the data of the user action.
     *
     * @return the data of this action
     */
    String getData();
}
