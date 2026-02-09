package it.unibo.makeanicecream.model.customermodel;

import java.util.Objects;
import java.util.function.Consumer;

import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.api.Timer;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Concrete implementation of the Customer Interface.
 * Represents a customer with an order, waiting timer, and result notification.
 * 
 * Timer exposure is accepted because is needed for game loop integration.
 */

public class CustomerImpl implements Customer {
  private final String name;
  private final Order order;
  private final Timer timer;
  private final int difficulty;
  private Consumer<Boolean> orderResultCallback;

  /**
   * Constructs a new Costumer with the attributes
   * 
   * @param name the customer's name
   * @param order the customer's ice cream order
   * @param timer the customer's timer for the level
   * @param difficulty the customer's difficulty level (1-5)
   */
  public CustomerImpl(final String name, final Order order, final Timer timer, final int difficulty){
    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Il nome del cliente non puo essere vuoto");
    }
    if (order == null) {
        throw new IllegalArgumentException("L'ordine non puo essere null");
    }
    if (timer == null) {
        throw new IllegalArgumentException("Il timer non puo essere null");
    }
    if (difficulty < 1 || difficulty > 5) {
        throw new IllegalArgumentException("La difficolta deve essere tra 1 e 5");
    }

    this.name = name;
    this.order = order;
    this.difficulty = difficulty;
    this.timer = Objects.requireNonNull(timer, "Il timer non puo essere null");
    
    if (timer.isExpired()) {
        this.timer.pause();
    } else {
        this.timer.start();
    }
  }

  @Override
  public boolean receiveIceCream(final Icecream iceCream) {
    Objects.requireNonNull(iceCream, "L'ice cream non puo essere null");

    final boolean isCorect = order.isSatisfiedBy(iceCream);

    // Notifica il risultato se è stato registrato un callback
    if(orderResultCallback != null){
        orderResultCallback.accept(isCorect);
    }
    return isCorect;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Order getOrder() {
    return order;
  }

  /**
   * Return the customer's timer.
   * Exposure necessary for game loop updates and timeout checks.
   */
  @Override
  @SuppressFBWarnings(value="EI_EXPOSE_REP", justification = "Timer is a shared API component")
  public Timer getTimer() {
    return timer;
  }

  @Override
  public int getDifficulty() {
    return difficulty;
  }

  @Override
  public void setOrderResultCallback(final Consumer<Boolean> callback) {
    this.orderResultCallback = callback;
  }
}