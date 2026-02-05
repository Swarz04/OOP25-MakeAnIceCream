package it.unibo.makeanicecream.api;

import java.util.function.Consumer;

/**
 *
 */
public interface Customer {
    
    String getName();
    Order getOrder();
    Timer getTimer();
    int getDifficulty();
    boolean checkIceCream(Icecream icecream);
    void setOrderResultCallback(Consumer<Boolean> callback);
}
