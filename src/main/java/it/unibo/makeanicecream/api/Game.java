package it.unibo.makeanicecream.api;

/**
 * Represents a game session of "Make an Ice Cream".
 */
public interface Game {

    /**
     * Starts a new game at the specified level number.
     * 
     * @param levelNumber the level to start
     */
    void start(int levelNumber);

    /**
     * Returns the current level.
     * 
     * @return the current level
     */
    Level getCurrentLevel();

    /**
     * Returns the current state of the game.
     * 
     * @return the current state
     */
    GameState getState();

    /**
     * Returns the player of the game.
     * 
     * @return the player
     */
    Player getPlayer();

    /**
     * Pauses the game if it is currently in PLAYING state.
     */
    void pause();

    /**
     * Resumes the game if it is currently in PAUSED state.
     */
    void resume();

    /**
     * Interrupts the current game level and returns the game to the main menu state.
     */
    void returnToMenu();

    /**
     * Checks whether the game is over.
     * 
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();
}
