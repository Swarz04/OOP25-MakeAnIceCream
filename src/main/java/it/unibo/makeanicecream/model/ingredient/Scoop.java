package it.unibo.makeanicecream.model.ingredient;

import it.unibo.makeanicecream.api.Ingredient;

/**
 * Scoop class representing an ice cream scoop ingredient.
 */
public class Scoop implements Ingredient {

    private final FlavorType flavor;

    /**
     * Constructor with the specified flavor.
     * 
     * @param flavor the flavor of the scoop
     */
    public Scoop(final FlavorType flavor) {
        this.flavor = flavor;
    }

    /**
     * Get the flavor of the scoop.
     * 
     * @return the flavor
     */
    public FlavorType getFlavor() {
        return flavor;
    }

    /**
     * Get the type of the ingredient.
     * 
     * @return the ingredient type, in this case SCOOP
     */
    @Override
    public IngredientType getType() {
        return IngredientType.SCOOP;
    }

    /**
     * Compares this Scoop with another object for equality.
     * Two Scoop objects are considered equal if they have the same flavor.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof Scoop otherScoop)) {
            return false;
        }
        return this.flavor == otherScoop.flavor;
    }

    /**
     * Returns the hash code value for this Scoop.
     * The hash code is based on the flavor of the scoop.
     * 
     * @return the hash code value for this Scoop
     */
    @Override
    public int hashCode() {
        return flavor.hashCode();
    }
}
