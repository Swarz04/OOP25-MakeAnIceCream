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
     * 
     * @param name the name of the current customer
     */
    void showCustomer(String name);

    /**
     * Displays the order of the current customer.
     * 
     * @param order the order of the current customer
     */
    void showOrder(String order);

    /**
     * Displays the remaining time of the current customer.
     * 
     * @param timer the timer of the current customer
     */
    void showTimer(double timer);

    /**
     * Displays the remaining lives of the player.
     * 
     * @param lives the number of remaining lives
     */
    void showLives(int lives);

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
