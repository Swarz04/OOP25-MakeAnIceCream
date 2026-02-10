package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.model.ingredient.Scoop;
import it.unibo.makeanicecream.model.ingredient.LiquidTopping;
import it.unibo.makeanicecream.model.ingredient.SolidTopping;
import it.unibo.makeanicecream.api.FlavorType;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.LiquidToppingType;
import it.unibo.makeanicecream.api.SolidToppingType;

/**
 * Factory class for creating {@link Ingredient} instances based on their name.
 * This class provides a method to create an ingredient by its name, which can be a flavor, liquid topping, or solid topping.
 * The factory checks the name against known ingredient types and returns the appropriate instance.
 * If the name does not match any known ingredient, an IllegalArgumentException is thrown.
 */
public class IngredientFactory {

    /**
     * Creates an {@link Ingredient} instance based on the provided name.
     * 
     * @param name the name of the ingredient to create, which can be a flavor, liquid topping, or solid topping
     * @return the created ingredient instance
     */
    public Ingredient createIngredient(final String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ingredient name cannot be null or blank");
        }

        for(final FlavorType flavor : FlavorType.values()) {
            if(flavor.name().equals(name)) {
                return new Scoop(flavor);
            }
        }

        for(final LiquidToppingType liquidTopping : LiquidToppingType.values()) {
            if(liquidTopping.name().equals(name)) {
                return new LiquidTopping(liquidTopping);
            }
        }

        for(final SolidToppingType solidTopping : SolidToppingType.values()) {
            if(solidTopping.name().equals(name)) {
                return new SolidTopping(solidTopping);
            }
        }

        throw new IllegalArgumentException("Unknown ingredient name: " + name);
    }

}
