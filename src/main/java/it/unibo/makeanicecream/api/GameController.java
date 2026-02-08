package it.unibo.makeanicecream.api;

/**
 * Implementation of the game controller.
 * <p>
 * This class coordinates the game execution by acting as the Control component
 * in the MVC/ECB architecture. It receives user actions from the Boundary
 * components (views), updates the game state accordingly, and notifies the
 * boundaries about changes to be displayed.
 * </p>
 */

public interface GameController {

    /**
     * Handles a user action coming from the view.
     * 
     * @param action the user action to be processed
     */
    void handleInput(Event action);

    /**
     * Updates the game state.
     * 
     * @param deltaTime the time in seconds since tha last update
     */
    void updateGame(double deltaTime);

    /**
     * Sets a new view.
     *
     * @param view the view to be set
     */
    void setView(GameView view);

    /**
     * Checks if the game is currently in a playing state.
     * The game is considered playing when a level has been started
     * and is neither paused nor completed.
     *
     * @return true if the game is in the PLAYING state, false otherwise
     */
    boolean isGamePlaying();

}
