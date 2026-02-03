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
     * @param da aspettare come si compongono i gelati IceCram.java
     *
     * @return the composed {@link Icecream}
     */
    @Override
    public Icecream composeIceCream(final Ingredient ingredients) {
        Objects.requireNonNull(ingredients, "ingredients must not be null");
        /* TODO:aggiungere 1 ingrediente tramite vie-> controller e creare un IceCream o qualcosa del genere da Icecrea.java
        */
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
        /* TODO: implement order validation in the controller (non ho capito se serve altro qui)
        * getCustomer.getOrder().equals(this.currentIcecream) o qualcosa del genere da Customer.java
        */
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
