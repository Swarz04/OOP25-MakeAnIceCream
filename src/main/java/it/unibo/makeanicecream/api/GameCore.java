package it.unibo.makeanicecream.api;

/**
 * Represents the core of the game.
 * 
 * <p>
 * Implementations of this interface are responsible for initializing
 * the main components of the game, such as the game model, controller,
 * and game loop.
 * </p>
 */
@FunctionalInterface
public interface GameCore {
    /**
     * Returns the game controller associated with this engine.
     *
     * @return the game controller instance
     */
    GameController getController();
}
