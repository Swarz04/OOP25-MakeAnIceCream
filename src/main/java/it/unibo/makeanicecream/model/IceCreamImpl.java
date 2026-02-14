package it.unibo.makeanicecream.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;

/**
 * IceCreamImpl class implementing the Icecream interface.
 * This class models the state of an ice cream, including cone type,
 * flavors, liquid toppings, and optional solid topping.
 * It contains no construction logic and is updated by the builder.
 */
public final class IceCreamImpl implements Icecream {
    private final Conetype conetype;
    private final List<Ingredient> ingredients;
    private final boolean isClosed;

    /**
     * Constructor.
     * 
     * @param conetype the type of cone
     * @param ingredients the list of ingredients
     * @param isClosed whether the ice cream is closed (solid topping present)
     */
    public IceCreamImpl(final Conetype conetype, final List<Ingredient> ingredients, final boolean isClosed) {
        this.conetype = conetype;
        this.ingredients = new ArrayList<>(ingredients);
        this.isClosed = isClosed;
    }

    /**
     * Returns the type of cone.
     * 
     * @return the conetype
     */
    @Override
    public Conetype getConetype() {
        return conetype;
    }

    /**
     * Returns the list of ingredients.
     * 
     * @return an list of ingredients
     */
    @Override
    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(new ArrayList<>(ingredients));
    }

    /**
     * Returns whether the ice cream is closed (solid topping present).
     * 
     * @return true if closed, false otherwise
     */
    @Override
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Return a string representation for this icecream.
     * 
     * @return string containing icecream details
     */
    @Override
    public String toString() {
        final String coneString = (conetype == null) ? "-" : conetype.name();
        final String ingredientsString = ingredients.isEmpty()
            ? "(empty)"
            : ingredients.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        return "<html>"
            + "<b>Cone:</b> " + coneString
            + "<br><b>Ingredients:</b> " + ingredientsString
            + "</html>";
    }
}
