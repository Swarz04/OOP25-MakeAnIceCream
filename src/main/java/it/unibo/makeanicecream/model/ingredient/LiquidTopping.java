package it.unibo.makeanicecream.model.ingredient;

import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.LiquidToppingType;

/**
 * LiquidTopping class representing a liquid topping ingredient.
 */
public class LiquidTopping implements Ingredient {

    private final LiquidToppingType topping;

    /**
     * Constructor for LiquidTopping.
     * 
     * @param topping the liquid topping type
     */
    public LiquidTopping(final LiquidToppingType topping) {
        this.topping = topping;
    }

    /**
     * Returns the liquid topping.
     * 
     * @return the liquid topping type
     */
    public LiquidToppingType getTopping() {
        return topping;
    }

    /**
     * Returns the ingredient type.
     * 
     * @return the ingredient type which is LIQUID_TOPPING
     */
    @Override
    public IngredientType getType() {
        return IngredientType.LIQUID_TOPPING;
    }

    /**
     * Compares this LiquidTopping with another object for equality.
     * Two LiquidTopping objects are considered equal if they have the same topping type.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        return this == obj || obj instanceof LiquidTopping otherLiquidTopping && this.topping == otherLiquidTopping.topping;
    }

    /**
     * Returns the hash code value for this LiquidTopping.
     * The hash code is based on the topping type.
     * 
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return topping.hashCode();
    }

    /**
     * Returns a string representation of the LiquidTopping.
     * 
     * @return a string representation of the LiquidTopping
     */
    @Override
    public String toString() {
        return "[" + topping + "]";
    }
}
