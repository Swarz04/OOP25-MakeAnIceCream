package it.unibo.makeanicecream.model.customermodel;

import java.util.Arrays;

/**
 * Template defining customer characteristics for a specified difficulty level.
 * Used by CustomerFactory to generate consistent customers of each difficulty.
 */
public class CustomerTemplate {
    private final String[] possibleNames;
    private final int scoopCount;
    private final int toppingCount;
    private int nameCounter = 0;

    /**
     * Constructs a CustomerTemplate with the specified configuration.
     * Note: Time is NOT included here as it's determined by the game level.
     * 
     * @param names possible names for costumers of this template.
     * @param scoops number of flavor scoops required.
     * @param toppings number of toppings required.
     */
    public CustomerTemplate(final String[] names, final int scoops, final int toppings) {
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("Deve essere fornito almeno un nome");
        }
        if (scoops <= 0) {
            throw new IllegalArgumentException("Il numero deve essere positivo");   
        }
        if (toppings < 0) {
            throw new IllegalArgumentException("Il numero di topping non puo essere negativo");
        }

        this.possibleNames = Arrays.copyOf(names, names.length);
        this.scoopCount = scoops;
        this.toppingCount = toppings;
    }

    /**
     * Gets the next customer according to this template's naming scheme.
     * For templates with 2 names (first level), alternates between them; for 1 name, always returns it.
     * 
     * @return the next customer name to use.
     */
    public String getNextName() {
        if (possibleNames.length == 2) {
            final String name = possibleNames[nameCounter % 2];
            nameCounter++;
            return name;
        } else {
            return possibleNames[0];
        }

    }

    /**
     * Gets the number of flavor scoops for this template.
     * 
     * @return number of scoops.
     */
    public int getScoopCount() {
        return scoopCount;
    }

    /**
     * Gets the number of toppings for this template.
     * 
     * @return number of toppings.
     */
    public int getToppingCount() {
        return toppingCount;
    }

    /**
     * Gets a copy of the possible names array.
     * 
     * @return copy of possible names.
     */
    public String[] getPossibleNames() {
        return Arrays.copyOf(possibleNames, possibleNames.length);
    }

    /**
     * Returns a string representation of this template.
     * 
     * @return string containing template configuration.
     */
    @Override
    public String toString() {
        return String.format("CustomerTemplate[names=%s, scoops=%d, toppings=%d]",
        Arrays.toString(possibleNames), scoopCount, toppingCount);
    }
}
