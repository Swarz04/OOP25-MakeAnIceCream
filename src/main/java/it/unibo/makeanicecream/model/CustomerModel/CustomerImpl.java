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
  public static final int MAX_DIFFICULTY = 5;
  public static final int MIN_DIFFICULTY = 1;
  private final String name;
  private final Order order;
  private final Timer timer;
  private final int difficulty;
  private Consumer<Boolean> orderResultCallback;

  /**
   * Constructs a new Costumer with the attributes.
   * 
   * @param name the customer's name.
   * @param order the customer's ice cream order.
   * @param timer the customer's timer for the level.
   * @param difficulty the customer's difficulty level (1-5).
   */
  public CustomerImpl(final String name, final Order order, final Timer timer, final int difficulty) {
    if (name == null || name.isBlank()) {
        throw new IllegalArgumentException("Il nome del cliente non puo essere vuoto");
    }
    if (order == null) {
        throw new IllegalArgumentException("L'ordine non puo essere null");
    }
    if (timer == null) {
        throw new IllegalArgumentException("Il timer non puo essere null");
    }
    if (difficulty < MIN_DIFFICULTY || difficulty > MAX_DIFFICULTY) {
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

  /**
   * Receives an iceCream and checks if it matches the order.
   * Notifies the result if a Callback was registred.
   */
  @Override
  public boolean receiveIceCream(final Icecream iceCream) {
    Objects.requireNonNull(iceCream, "L'ice cream non puo essere null");

    final boolean isCorect = order.isSatisfiedBy(iceCream);

    if (orderResultCallback != null) {
        orderResultCallback.accept(isCorect);
    }
    return isCorect;
  }

  /**
   * Gets the customer's name.
   * 
   * @return costumer's name.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the customers's order.
   * 
   * @return Order of the costumer.
   */
  @Override
  public Order getOrder() {
    return order;
  }

  /**
   * Return the customer's timer.
   * Exposure necessary for game loop updates and timeout checks.
   * 
   * @return timer del cliente.
   */
  @Override
  @SuppressFBWarnings(value="EI_EXPOSE_REP", justification = "Timer is a shared API component")
  public Timer getTimer() {
    return timer;
  }

  /**
   * returns the level difficulty of the customer.
   * 
   * @return difficulty of the costumer.
   */
  @Override
  public int getDifficulty() {
    return difficulty;
  }

  /**
   * Registers a callback to be notified when the specified costumer receives an ice cream.
   * The callback receives a boolean value that indicates if the order was satisfied.
   * 
   * @param callback the consumer to notify the verification result.
   */
  @Override
  public void setOrderResultCallback(final Consumer<Boolean> callback) {
    this.orderResultCallback = callback;
  }
}