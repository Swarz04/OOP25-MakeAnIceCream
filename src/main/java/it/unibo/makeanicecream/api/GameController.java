package it.unibo.makeanicecream.api;

/**
 * Interface representing the game controller.
 * 
 * <p>
 * This class coordinates the game execution by acting as the Control component
 * in the MVC/ECB architecture. It receives events from the Boundary
 * components (views), updates the game state accordingly, and notifies the
 * boundaries about changes to be displayed.
 * </p>
 */

public interface GameController {

    /**
     * Handles a user action coming from the view.
     * 
     * @param event the user action to be processed
     * 
     * @throws IllegalArgumentException if the event is null or its type is unknown
     */
    void handleInput(Event event);

    /**
     * Updates the game state.
     * 
     * @param deltaTime the time in seconds since the last update
     */
    void updateGame(double deltaTime);

    /**
     * Sets a new view to be controlled by this controller.
     *
     * @param view the view to be set
     */
    void setView(GameView view);

    /**
     * Checks if the game is currently in a playing state.
     * 
     * <p>
     * The game is considered playing when a level has been started
     * and is neither paused nor completed.
     * </p>
     *
     * @return true if the game is in the PLAYING state, false otherwise
     */
    boolean isGamePlaying();

    /**
     * Returns whether toppings are currently enabled in the game.
     *
     * @return true if toppings are enabled, false otherwise
     */
    boolean areToppingsEnabled();

    /**
     * Returns the current state of the game.
     *
     * @return the current game state
     */
    GameState getGameState();

    /**
     * Returns the current ice cream being built in the game.
     *
     * @return the current ice cream of the game
     */
    Icecream getGameIceCream();

    /**
     * Returns the difficulty of the current level.
     *
     * @return the difficulty of the active level
     * 
     * @throws IllegalStateException if no level has been started yet
     */
    int getLevelDifficulty();
}
