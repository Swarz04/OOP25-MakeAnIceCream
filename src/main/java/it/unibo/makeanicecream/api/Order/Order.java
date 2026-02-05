package it.unibo.makeanicecream.api.Order;

import it.unibo.makeanicecream.api.Icecream;

/**
 *
 */
public interface Order {

    boolean isSatisfiedBy(Icecream icecream);       
}