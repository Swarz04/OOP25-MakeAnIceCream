package it.unibo.makeanicecream.model;

import java.util.Objects;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Player;

/**
 * PlayerImpl is the implementation of the Player interface.
 */
public final class PlayerImpl implements Player {

    private final IceCreamBuilder builder;

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
    @Override
    public boolean chooseCone(final Conetype conetype) {
        return this.builder.chooseCone(conetype);
    }

    /**
     * Adds an ingredient to the ice cream.
     *
     * @param ingredient the ingredient to add
     * @return true if the ingredient was added, false otherwise
     */
    @Override
    public boolean addIngredient(final Ingredient ingredient) {
        return this.builder.addIngredient(ingredient);
    }

    /**
     * Composes the current ice cream.
     * Submits the builder state to create a final IceCreamImpl instance.
     *
     * @return the composed {@link Icecream}
     */
    @Override
    public Icecream composeIceCream() {
        return this.builder.submit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deliverIceCream(final Customer customer) {
        if (Objects.isNull(customer)) {
            return false;
        }

        final Icecream iceCream = composeIceCream();
        final boolean success = customer.receiveIceCream(iceCream);

        this.builder.reset();
        return success;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelIceCream() {
        this.builder.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Icecream getCurrentIceCream() {
        return this.builder.getIceCream();
    }

    @Override
    public void setToppingEnabled(boolean enabled) {
        this.builder.setToppingEnabled(enabled);
    }
}
