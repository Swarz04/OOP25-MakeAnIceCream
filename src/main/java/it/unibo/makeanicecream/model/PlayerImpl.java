package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Player;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.model.ingredient.Conetype;

import java.util.Objects;

/**
 * PlayerImpl is the implementation of the Player interface.
 */
public class PlayerImpl implements Player {

    private Icecream currentIcecream;
    private IceCreamBuilder builder;

    /**
     * Builds a new Player instance.
     */
    public PlayerImpl() {
        this.builder = new IceCreamBuilder();
    }

    /**
     * Selects the cone type.
     *
     * @param conetype the cone type
     * @return true if the cone was selected, false otherwise
     */
    public boolean chooseCone(final Conetype conetype) {
        return this.builder.chooseCone(conetype);
    }

    /**
     * Adds an ingredient to the ice cream.
     *
     * @param ingredient the ingredient to add
     * @return true if the ingredient was added, false otherwise
     */
    public boolean addIngredient(final Ingredient ingredient) {
        Objects.requireNonNull(ingredient, "ingredient must not be null");
        return this.builder.addIngredient(ingredient);
    }

    /**
     * Checks if the ice cream is closed.
     *
     * @return true if the ice cream is closed, false otherwise
     */
    public boolean isIceCreamClosed() {
        return this.builder.isClosed();
    }

    /**
     * Enables or disables toppings.
     *
     * @param enabled true to enable toppings, false to disable
     */
    public void setToppingEnabled(final boolean enabled) {
        this.builder.setToppingEnabled(enabled);
    }

    /**
     * Compose the current ice cream.
     * Submits the builder state to create a final IceCreamImpl instance.
     *
     * @return the composed {@link Icecream}
     */
    @Override
    public Icecream composeIceCream() {
        this.currentIcecream = this.builder.submit();
        return this.currentIcecream;
    }

    /**
     * {@inheritDoc}
     *
     * @param customer the customer to deliver to
     */
    @Override
    public boolean deliverIceCream(final Customer customer) {
        if (Objects.isNull(customer)) {
            return false;
        }

        composeIceCream();
        final boolean success = customer.receiveIceCream(this.currentIcecream);

        this.builder.reset();
        this.currentIcecream = null;
        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelIceCream() {
        this.builder.reset();
        this.currentIcecream = null;
    }
}