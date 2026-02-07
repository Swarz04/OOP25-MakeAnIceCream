package it.unibo.makeanicecream.model.customermodel;

import java.util.Arrays;

/**
 * Template defining customer characteristics for a specified difficulty level.
 * Used by CustomerFactory to generate consistent customers of each difficulty
 */
public class CustomerTemplate {
    private final String[] possibleNames;
    private final int scoopCount;
    private final int toppingCount;
    private int nameCounter = 0;

    /**
     * 
     * Constructs a CustomerTemplate with the specified configuration.
     * Note: Time is NOT included here as it's determined by the game level
     * 
     * @param names possible names for costumers of this template
     * @param scoops number of flavor scoops required
     * @param toppings number of toppings required
     */
    public CustomerTemplate(String[] names, int scoops, int toppings){

    }
}
