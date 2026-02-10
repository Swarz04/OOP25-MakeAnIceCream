package it.unibo.makeanicecream.model.ingredient;

import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.SolidToppingType;

/**
 * SolidTopping class representing a solid topping ingredient.
 */

public class SolidTopping implements Ingredient {
    private final SolidToppingType topping;

    /**
     * Constructor.
     * 
     * @param topping the solid topping type
     */
    public SolidTopping(final SolidToppingType topping) {
        this.topping = topping;
    }

    /**
     * Returns the solid topping.
     * 
     * @return the solid topping type
     */
    public SolidToppingType getTopping() {
        return topping;
    }

    /**
     * Returns the ingredient type.
     * 
     * @return the ingredient type which is SOLID_TOPPING
     */
    @Override
    public IngredientType getType() {
        return IngredientType.SOLID_TOPPING;
    }

    /**
     * Compares this SolidTopping with another object for equality.
     * Two SolidTopping objects are considered equal if they have the same topping type.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SolidTopping otherSolidTopping)) {
            return false;
        }
        return this.topping == otherSolidTopping.topping;
    }

    /**
     * Returns the hash code value for this SolidTopping.
     * The hash code is based on the topping type.
     * 
     * @return the hash code value for this SolidTopping
     */
    @Override
    public int hashCode() {
        return topping.hashCode();
    }
}
