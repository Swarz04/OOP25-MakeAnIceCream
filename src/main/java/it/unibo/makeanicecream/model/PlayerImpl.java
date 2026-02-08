package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Player;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.model.ingredient.Conetype;

import java.util.List;
import java.util.Objects;

/**
 * PlayerImpl is the implementation of the Player interface.
 */
public class PlayerImpl implements Player {

    private Icecream currentIcecream;
    private IceCreamBuilder builder;

    public PlayerImpl() {
        this.builder = new IceCreamBuilder();
    }

    public boolean chooseCone(final Conetype conetype) {
        return this.builder.chooseCone(conetype);
    }

    public boolean addIngredient(final Ingredient ingredient) {
        Objects.requireNonNull(ingredient, "ingredient must not be null");
        return this.builder.addIngredient(ingredient);
    }

    public boolean isIceCreamClosed() {
        return this.builder.isClosed();
    }

    public void setToppingEnabled(final boolean enabled) {
        this.builder.setToppingEnabled(enabled);
    }

    /**
     * Compose the current ice cream.
     * Submits the builder state to create a final IceCreamImpl instance.
     *
     * @param ingredients the list of ingredients (managed internally by builder)
     * @return the composed {@link Icecream}
     */
    @Override
    public Icecream composeIceCream() {
        this.currentIcecream = this.builder.submit();
        return this.currentIcecream;
    }

    @Override
    public boolean deliverIceCream(final Customer customer) {
        if (Objects.isNull(this.currentIcecream) || Objects.isNull(customer)) {
            return false;
        }
        composeIceCream();
        /* TODO: Implement order validation when Customer interface provides getOrder() method.
        * Compare: customer.getOrder() with currentIcecream
        * (cone type, ingredients, isClosed state)
        */
        this.currentIcecream = null;
        return true;
    }

    /**
     * Cancel the current composition.
     */
    @Override
    public void cancelIceCream() {
        this.builder.reset();
        this.currentIcecream = null;
    }
}
