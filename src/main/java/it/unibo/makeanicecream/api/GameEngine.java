package it.unibo.makeanicecream.api;

/**
 * Represents the main entry point of the game runtime.
 * 
 * <p>
 * The GameEngine is responsible for initializing and coordinating
 * the core components of the application, such as the game loop and the
 * game controller. It controls the lifecycle of the game execution.
 * </p>
 */

public interface GameEngine {
    /**
     * Returns the game controller associated with this engine.
     *
     * @return the game controller instance
     */
    GameController getController();
}
