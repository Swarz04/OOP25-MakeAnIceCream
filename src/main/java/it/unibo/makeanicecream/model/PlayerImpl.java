package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Player;

import java.util.List;
import java.util.Objects;

/**
 *
 */
public class PlayerImpl implements Player {

    private Icecream currentIcecream;

    /**
     *
     * @param ingredients the ingredients to compose the ice-cream from
     *
     * @return the composed {@link Icecream}
     */
    @Override
    public Icecream composeIceCream(final List<Ingredient> ingredients) {
        Objects.requireNonNull(ingredients, "ingredients must not be null");
        this.currentIcecream = Icecream.compose(ingredients);
        return this.currentIcecream;
    }

    /**
     * @param customer the recipient
     *
     * @return true if the delivery was successful, false otherwise
     */
    @Override
    public boolean deliverIceCream(final Customer customer) {
        if (Objects.isNull(this.currentIcecream) || Objects.isNull(customer)) {
            return false;
        }
        // TODO: implement order validation in the controller (non ho capito se serve altro qui)
        this.currentIcecream = null;
        return true;
    }

    /**
     * Cancel the current composition.
     */
    @Override
    public void cancelIceCream() {
        this.currentIcecream = null;
    }

}
