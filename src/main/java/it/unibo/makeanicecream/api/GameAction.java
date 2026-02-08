package it.unibo.makeanicecream.api;

/**
 * Represents an action performed by the user in the game.
 */
public interface GameAction {
    
    /**
     * Returns the type of the user action.
     * 
     * @return the type of this action
     */
    GameActionType getType();
}
