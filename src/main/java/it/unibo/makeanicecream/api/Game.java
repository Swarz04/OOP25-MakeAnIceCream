package it.unibo.makeanicecream.api;

/**
 * Represents a game session of "Make an Ice Cream".
 */
public interface Game {

    /**
     * Starts a new game at the specified level number.
     * 
     * <p>
     * Initializes the level, player, and sets the game state to PLAYING.
     * </p>
     *
     * @param levelNumber the level to start, must be positive
     * 
     * @throws IllegalArgumentException if levelNumber is not positive
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

    /**
     * Checks whether the game is currently paused.
     * 
     * @return true if the game is paused, false otherwise
     */
    boolean isPaused();

    /**
     * Checks whether the game is being played.
     * 
     * @return true if the game is being played, false otherwise
     */
    boolean isPlaying();

    /**
     * Updates the game state.
     * 
     * <p>
     * Delegates to the current level to update customer timers and manage
     * lives and progress.
     * </p>
     * 
     * @param deltaTime the time in seconds since the last update
     */
    void update(double deltaTime);
}
