package it.unibo.makeanicecream.api;

/**
 * Implementation of the game controller.
 */

public interface GameController {

    /**
     * Starts a new game at the specified level.
     * 
     * @param levelNumber the number of the level
     */
    void startGame(int levelNumber);

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

}
