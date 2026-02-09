package it.unibo.makeanicecream.api;

import java.util.function.Consumer;

/**
 * Interface that represents a customer inside the ice cream game.
 * Each customer has an order, a waiting timer, and can receive ice creams.
 * Customers can notify a callback when they receive an ice cream.
 */
public interface Customer {

    /**
     * Gets the customer's name.
     * 
     * @return the customer's name.
     */
    String getName();

    /**
     * Gets the customer's order.
     * 
     * @return the costumer's order.
     */
    Order getOrder();

    /**
     * Gets the customer's timer for waiting.
     * 
     * @return the customer's timer.
     */
    Timer getTimer();

    /**
     * Gets the customer's difficulty level.
     * Higher difficulty levels indicate more complex orders.
     * 
     * @return the difficulty level (1-5).
     */
    int getDifficulty();

    /**
     * Receives an ice cream and verifies if it matches the customer's order.
     * 
     * @param iceCream the ice cream.
     * @return true if the ice cream satisfies the order, false otherwise.
     */
    boolean receiveIceCream(Icecream iceCream);

    /**
     * Sets a callback to be notified when the costumer receives an icecream.
     * The callback receives a boolean if the order was satisfied by icecream.
     *
     *  @param callback the consumer to notify the verification result.
     */
    void setOrderResultCallback(Consumer<Boolean> callback);
}
