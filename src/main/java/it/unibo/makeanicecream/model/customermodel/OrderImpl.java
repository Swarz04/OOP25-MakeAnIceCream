package it.unibo.makeanicecream.model.customermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.model.ingredient.Conetype;
import it.unibo.makeanicecream.model.ingredient.IngredientType;

/**
 * Implementation of the Order Interface representing a customer's ice cream order.
 * An order consist of flavors (scoops), a cone, and optional toppings.
 * This class can verify if an IceCream satisfies the order requirements.
 * 
 */
public class OrderImpl implements Order {

    private final List<Ingredient> requiredFlavors;
    private final Conetype requiredCone;
    private final List<Ingredient> requiredToppings;

    /**
     * Constructs a new Order with the specified components.
     * 
     * @param flavors the list of flavor scoops required (can't be empty).
     * @param cone the cone required (can't be null).
     * @param toppings the list of toppings required (can be empty).
     */
    public OrderImpl(final List<Ingredient> flavors, final Conetype cone, final List<Ingredient> toppings) {
        validateConstructorArguments(flavors, cone, toppings);
        this.requiredFlavors = new ArrayList<>(flavors);
        this.requiredCone = cone;
        this.requiredToppings = new ArrayList<>(toppings);
    }

    /**
     * Validates constructor arguments.
     * 
     * @param flavors list of flavors to validate.
     * @param cone the cone type to validate.
     * @param toppings the list of toppings to validate.
     */
    private void validateConstructorArguments(final List<Ingredient> flavors, final Conetype cone,
         final List<Ingredient> toppings) {
        if (flavors == null || flavors.isEmpty()) {
            throw new IllegalArgumentException("L'ordine deve contenere almeno un gusto");
        }

        for (final Ingredient flavor : flavors) {
            if (flavor.getType() != IngredientType.SCOOP) {
                throw new IllegalArgumentException("I flavor devono essere di tipo scoop");
            }
        }

        if (cone == null) {
            throw new IllegalArgumentException("L'ordine deve specificare un cono");
        }

        if (toppings != null) {
            for (final Ingredient topping: toppings) {
                if (topping.getType() != IngredientType.LIQUID_TOPPING && topping.getType()
                     != IngredientType.SOLID_TOPPING) {
                    throw new IllegalArgumentException("i toppings devono essere LIQUID o SOLID");
                }
            }
        }
    }

    /**
     * Gets the list of flavor scoops required by this order.
     *
     * @return an unmodifiable view of the required flavors.
     */
    @Override
    public List<Ingredient> getFlavors() {
        return Collections.unmodifiableList(requiredFlavors);
    }

    /**
     * Gets the cone required by this order.
     * 
     * @return the requiredCone. 
     */
    @Override
    public Conetype getRequestedConeType() {
        return requiredCone;
    }

    /**
     * Gets the list of toppings required by this order.
     *
     *  @return an unmodifiable view of the required toppings.
     */
    @Override
    public List<Ingredient> getToppings() {
        return Collections.unmodifiableList(requiredToppings);
    }

    /**
     * Verifies if the provided ice cream satisfies the order by an exact match.
     * The ice cream must contain all required flavors, the cone, and all toppings.
     * 
     * @param iceCream the ice cream to check against this order.
     * @return true if the ice cream satisfies all the order requirements, false otherwise.
     */
    @Override
    public boolean isSatisfiedBy(final Icecream iceCream) {
        Objects.requireNonNull(iceCream, "L'ice cream non puo essere null");

        if (iceCream.getConetype() != requiredCone) {
            return false;
        }
        final List<Ingredient> iceCreamIngredients = iceCream.getIngredients();

        return haveSameIngredients(getAllRequiredIngredients(), iceCreamIngredients);
    }

    /**
     * Checks if two list contain exactly the same ingredients (same elements, same counts).
     * 
     * @param list1 first list of ingredients.
     * @param list2 second list of ingredients.
     * @return true if lists contain exactly the same ingredients, false otherwise.
     */
    private boolean haveSameIngredients(final List<Ingredient> list1, final List<Ingredient> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        final Map<Ingredient, Integer> counts1 = countIngredients(list1);
        final Map<Ingredient, Integer> counts2 = countIngredients(list2);

        return counts1.equals(counts2);
    }

    /**
     * Counts occurrences of each unique ingredient in a list.
     * 
     * @param ingredients list to count.
     * @return map from ingredient to its count in the list.
     */
    private Map<Ingredient, Integer> countIngredients(final List<Ingredient> ingredients) {
        final Map<Ingredient, Integer> counts = new HashMap<>();
        for (final Ingredient ingredient : ingredients) {
            counts.put(ingredient, counts.getOrDefault(ingredient, 0) + 1);
        }
        return counts;
    }
    
    /**
     * Helper: gets all required ingredient requirements.
     * 
     * @return the required ingredient requirements
     */
    private List<Ingredient> getAllRequiredIngredients() {
        final List<Ingredient> all = new ArrayList<>();
        all.addAll(requiredFlavors);
        all.addAll(requiredToppings);

        return all;
    }

    /**
     * Returns a string representation of this order.
     * 
     * @return string containing order details.
     */
    @Override
    public String toString() {
        return String.format("OrderImpl[flavors=%s, cone=%s, toppings=%s]",
        requiredFlavors, requiredCone, requiredToppings);
    }
}
