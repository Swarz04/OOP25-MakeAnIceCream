package it.unibo.makeanicecream.model;

import api.Order;
import it.unibo.makeanicecream.api.Ingredient;

/**
 * Builder that allows to create Order instances with a fluent interface.
 * Ensures that constructed orders are valid before building.
 */
public class OrderBuilder {
    private final List<Ingredient> flavors = new ArrayList<>();
    private Conetype cone;
    private final List<Ingredient> toppings = new ArrayList<>();

    /**
     * Adds a  flavor scoop to the order being built
     * 
     * @param flavor the flavor ingredient to add (must be of type SCOOP)
     * @return this builder for method chaining
     */
}
