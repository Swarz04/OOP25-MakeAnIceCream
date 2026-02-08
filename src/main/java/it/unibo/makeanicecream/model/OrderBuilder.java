package it.unibo.makeanicecream.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.model.customermodel.OrderImpl;
import it.unibo.makeanicecream.model.ingredient.Conetype;
import it.unibo.makeanicecream.model.ingredient.IngredientType;

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
    public OrderBuilder addFlavor(Ingredient flavor) {
        if (flavor == null) {
            throw new IllegalArgumentException("Flavor non puo essere null");
        }
        if (flavor.getType() != IngredientType.SCOOP) {
            throw new IllegalArgumentException("L'ingrediente deve essere di tipo SCOOP");
        }
        flavors.add(flavor);
        return this;
    }

    /**
     * Sets the cone type for the order being built
     * 
     * @param cone the cone type to use
     * @return this builder for method chaining
     * 
     */
    public OrderBuilder setCone(Conetype cone) {
        if (cone == null) {
            throw new IllegalArgumentException("Cone non puo essere null");
        }
        this.cone = cone;
        return this;
    }

    public OrderBuilder addTopping(Ingredient topping) {
        if (topping == null) {
            throw new IllegalArgumentException("Topping non puo essere null");
        }
        if (topping.getType() != IngredientType.LIQUID_TOPPING && topping.getType() != IngredientType.SOLID_TOPPING) {
            throw new IllegalArgumentException("L'ingrediente deve essere un toppin valido");
        }
        toppings.add(topping);
        return this;
    }

    /**
     * Builds and returns an Order based on the configured components
     * 
     * @return a new Order instance 
     */
    public Order build() {
        if (cone == null) {
            throw new IllegalStateException("Deve specificare un cono");
        }
        if (flavors.isEmpty()) {
            throw new IllegalStateException("Deve avere almeno un gusto");
        }
        return new OrderImpl(flavors, cone, toppings);
    }

    /**
     * Resets this builder to its initial empty state
     * 
     * @return this builder for method chaining
     */
    public OrderBuilder reset() {
        flavors.clear();
        cone = null;
        toppings.clear();
        return this;
    }
    /**
     * Returns a string representation of this builder's current state.
     * 
     * @return string containing builder configuration
     */
    @Override
    public String toString() {
        return String.format("OrderBuilder[flavors=%d, cone=%s, toppings=%d]", 
        flavors.size(), cone, toppings.size());
    }
}