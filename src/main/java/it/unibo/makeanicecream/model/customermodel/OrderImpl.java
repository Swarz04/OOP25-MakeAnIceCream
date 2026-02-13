package it.unibo.makeanicecream.model.customermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Order;
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
        if (toppings == null) {
            this.requiredToppings = new ArrayList<>();
        } else {
            this.requiredToppings = new ArrayList<>(toppings);
        }
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
            int solidCount = 0;
            for (final Ingredient topping: toppings) {
                if (topping.getType() == IngredientType.SOLID_TOPPING) {
                    solidCount++;
                }
            }
            if (solidCount > 1) {
                throw new IllegalArgumentException("Al massimo un topping solido alla fine per ordine");
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
        final List<Ingredient> actualIngredients = iceCream.getIngredients();
        final List<Ingredient> expectedIngredients = getAllRequiredIngredients();

        if (expectedIngredients.size() != actualIngredients.size()) {
            return false;
        }
        for (int i = 0; i < expectedIngredients.size(); i++) {
            if (!expectedIngredients.get(i).equals(actualIngredients.get(i))) {
                return false;
            }
        }
        return true;
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
        final List<Ingredient> allIngredients = getAllRequiredIngredients();

        final String ingredientString = allIngredients.isEmpty()
        ? "(empty)"
        : allIngredients.stream()
            .map(Ingredient::toString)
            .reduce((a, b) -> a + ", " + b)
            .orElse("");
        
        return "<html>" + "<b>Cone:</b> " + requiredCone + "<br><b>Ingredients:</b> " + ingredientString + "</html>";
    }
}
