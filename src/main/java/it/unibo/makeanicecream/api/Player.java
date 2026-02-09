package it.unibo.makeanicecream.api;

/**
 * Player behaviour as in the project UML.
 */
public interface Player {

	Icecream composeIceCream();

	boolean deliverIceCream(Customer customer);

	void cancelIceCream();

}
