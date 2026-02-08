package it.unibo.makeanicecream.api;

/**
 * Represents a game loop that periodically updates the game state
 * through the controller.
 */

public interface GameLoop {
    
    /**
     * Starts the game loop.
     */
    void start();

    /**
     * Stops the game loop.
     */
    void stop();

    /**
     * Returns true if the loop is currently running.
     * 
     * @return true if the loop is running, false otherwise
     */
    boolean isRunning();
}
