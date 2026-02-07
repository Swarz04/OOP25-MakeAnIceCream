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

    
    public CustomerTemplate(String[] names, int scoops, int toppings){

    }
}
