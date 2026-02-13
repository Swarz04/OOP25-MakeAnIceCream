package it.unibo.makeanicecream.api;

/**
 * Player behaviour.
 */
public interface Player {

    /**
     * Adds an ingredient to the current ice cream being prepared.
     *
     * @param ingredient the ingredient to add
     * @return true if the ingredient was successfully added, false otherwise
     */
    boolean addIngredient(Ingredient ingredient);

    /**
     * Chooses the cone type for the ice cream.
     *
     * @param conetype the type of cone
     * @return true if the cone was successfully chosen, false otherwise
     */
    boolean chooseCone(Conetype conetype);

    /**
     * Composes the final ice cream from the selected ingredients.
     *
     * @return the composed ice cream
     */
    Icecream composeIceCream();

    /**
     * Delivers the prepared ice cream to a customer.
     *
     * @param customer the customer to deliver to
     * @return true if the delivery was successful
     */
    boolean deliverIceCream(Customer customer);

    /**
     * Cancels the current ice cream preparation and clears all selected ingredients.
     */
    void cancelIceCream();

    /**
     * Gets the current ice cream being prepared.
     *
     * @return the current ice cream
     */
    Icecream getCurrentIceCream();

    /**
     * Enables or disables the possibility of adding toppings
     * while composing an ice cream.
     * 
     * @param enabled true to allow toppings to be added,
     *                false to prevent adding toppings
     */
    void setToppingEnabled(boolean enabled);
}
