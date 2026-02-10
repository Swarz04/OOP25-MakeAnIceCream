package it.unibo.makeanicecream.api;

/**
 * Player behaviour as in the project UML.
 */
public interface Player {

    boolean addIngredient(Ingredient ingredient);

    boolean chooseCone(Conetype conetype);

    Icecream composeIceCream();

    boolean deliverIceCream(Customer customer);

    void cancelIceCream();
}
