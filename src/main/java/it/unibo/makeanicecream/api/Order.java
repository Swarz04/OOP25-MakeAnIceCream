package it.unibo.makeanicecream.api;

import java.util.List;

import it.unibo.makeanicecream.model.ingredient.Conetype;

    /**
     * Interface representing a customer's ice cream order.
     * An order specifies the required flavos, cone and toppings.
     * Implementations should be able to verify if an Icecream satisfies the order.
     */
public interface Order {


    /**
     * Gets the list of flavor scoops required by this order.
     * Flavors should be of type IngredientType (SCOOP)
     * 
     * @return an unmodifiable list of required flavor ingredients
     */
    List<Ingredient> getFlavors(); 
    
    /**
     * Gets the cone required by this order
     * 
     * @return the required cone type 
     */
    Conetype getRequestedConeType(); 
    
    /**
     * Gets the list of toppings required by this order.
     * Toppings should be of type IngredientType (LIQUID_TOPPING/ SOLID_TOPPING)
     * 
     * @return an unmodifiable list of required topping ingredients
     */
    List<Ingredient>getToppings(); //Ingredient con Type=LIQUID_TOPPING O SOLID_TOPPING
    
    /**
     * Verifies if the provided ice cream satisfies the order.
     * The implementations should check if the ice cream contains
     * all required components in the correct quantities.
     * 
     * @param icecream the ice cream to check against the client order
     * @return true if the ice cream satisfies all order requirements, false otherwise
     */
    boolean isSatisfiedBy(Icecream icecream);

    /**
     * Returns a string representation of an order.
     * 
     * @return string containing order.
     */
    public String toString();
}