package it.unibo.makeanicecream.api;

/**
 * Represents a game session of "Make an Ice Cream".
 * 
 * <p>
 * This interface defines the main operations of a game session, including
 * starting levels, managing the current ice cream being prepared, updating
 * the game state, and controlling the game flow (pause, resume, return to menu).
 * </p>
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
     * Selects the cone type for the ice cream being prepared by the player.
     * 
     * @param cone the type of cone
     * @return true if the cone was successfully chosen, false otherwise
     */
    boolean chooseCone(Conetype cone);

    /**
     * Adds an ingredient to the current ice cream being prepared by the player.
     * 
     * @param ingredient the ingredient to add
     * @return true if the ingredient was successfully added, false otherwise
     */
    boolean addIngredient(Ingredient ingredient);

    /**
     * Delivers the current ice cream to a customer.
     * 
     * @param customer the customer to deliver to
     * @return true if the delivery was successful, false otherwise
     */
    boolean deliverIceCream(Customer customer);

    /**
     * Cancels the current ice cream preparation.
     */
    void cancelIceCream();

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
