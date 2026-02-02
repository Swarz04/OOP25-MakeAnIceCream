package it.unibo.makeanicecream.api;

/**
 * Represents an action performed by the user in the game.
 */
public interface GameAction<T> {
    
    /**
     * Returns the type of the user action.
     * 
     * @return the type of this action
     */
    T getType();
}
