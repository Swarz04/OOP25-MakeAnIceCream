package it.unibo.makeanicecream.api;

/**
 * Represents a view (boundary) architectural component of the application.
 */
public interface GameView {
    /**
     * Sets the controller controlled by this view (if works as input).
     *
     * @param controller the controller to attach
     */
    void setController(GameController controller);

    /**
     * Displays the current customer being served.
     */
    void showCustomer();

    /**
     * Displays the order of the current customer.
     */
    void showOrder();

    /**
     * Displays the remaining time of the current customer.
     */
    void showTimer();

    /**
     * Displays the remaining lives of the player.
     */
    void showLives();

    /**
     * Displays the avaiable ingredients.
     */
    void showIngredients();

    /**
     * Displays the ice cream currently being prepared by the player.
     */
    void showIceCream();

    /**
     * Starts the view and the game loop.
     */
    void start();

    /**
     * Stops the view and the game loop.
     */
    void stop();

    /**
     * Updates the view.
     */
    void update();
}
