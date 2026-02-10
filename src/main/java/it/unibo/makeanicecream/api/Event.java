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
     * Returns additional data related to the event.
     *
     * @return a string representing the ingredient name or the level number
     */
    String getData();
}
