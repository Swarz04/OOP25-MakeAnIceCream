package it.unibo.makeanicecream.api.Customer;

import it.unibo.makeanicecream.api.Icecream;
/**
 *
 */
public interface Customer {
    
    String getName();
    boolean checkIceCream(Icecream icecream);
}
