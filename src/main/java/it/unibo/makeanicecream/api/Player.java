package it.unibo.makeanicecream.api;

import it.unibo.makeanicecream.model.ingredient.Conetype;

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