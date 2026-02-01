package it.unibo.makeanicecream.api;

import java.util.List;

/**
 * Player behaviour as in the project UML.
 */
public interface Player {

	Icecream composeIceCream(List<Ingredient> ingredients);

	boolean deliverIceCream(Customer customer);

	void cancelIceCream();

}
