package it.unibo.makeanicecream.model.customermodel;

import java.util.Objects;
import java.util.function.Consumer;

import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.api.Timer;

/**
 * Concrete implementation of the Customer Interface.
 * Represents a customer with an order, waiting timer, and result notification.
 * 
 */
public class CustomerImpl implements Customer {
  private final String name;
  private final Order order;
  private final Timer timer;
  private final int difficulty;
  private Consumer<Boolean> orderResultCallback;

  public CustomerImpl(String name, Order order, Timer timer, int difficulty){
    if(name == null || name.trim().isEmpty()){
        throw new IllegalArgumentException("Il nome del cliente non puo essere vuoto");
    }
    if(order == null){
        throw new IllegalArgumentException("L'ordine non puo essere null");
    }
    if(timer == null){
        throw new IllegalArgumentException("Il timer non puo essere null");
    }
    if(difficulty < 1 || difficulty > 5){
        throw new IllegalArgumentException("La difficolta deve essere tra 1 e 5");
    }

    this.name = name;
    this.order = order;
    this.difficulty = difficulty;
    this.timer = createProtectedTimer(timer);
    
    if (timer.isExpired()) {
        this.timer.pause();
    } else {
        this.timer.start();
    }
}
 /**
  * Crea una versione protetta del timer.
  * Se il timer è immutabile (come CustomerTimer) lo usa direttamente.
  * Altrimenti crea un wrapper/delegato
  */ 
 private Timer createProtectedTimer(Timer original) {
    Objects.requireNonNull(original, "Il timer non puo essere null");
    
    if (original instanceof CustomerTimer) {
        return original;
    } else {
        return new TimerDelegate(original);
    }
 }

 /**
  * Delegato per proteggere il timer originale
  */
  private static class TimerDelegate implements Timer {
    private final Timer delegate;
  
    TimerDelegate(Timer delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }
    @Override public void start() { delegate.start(); }
    @Override public void pause() { delegate.pause(); }
    @Override public void resume() { delegate.resume(); }
    @Override public void update(double deltaTime){ delegate.update(deltaTime); }
    @Override public boolean isExpired() { return delegate.isExpired(); }
    @Override public double getTimeLeft() { return delegate.getTimeLeft(); }
    @Override public boolean isPaused() { return delegate.isPaused(); }
    @Override public void setOnExpired(Runnable callback) { delegate.setOnExpired(callback);}  
  }
    
  }
  @Override
  public boolean receiveIceCream(Icecream iceCream){
    Objects.requireNonNull(iceCream, "L'ice cream non puo essere null");

    boolean isCorect = order.isSatisfiedBy(iceCream);

    // Notifica il risultato se è stato registrato un callback
    if(orderResultCallback != null){
        orderResultCallback.accept(isCorect);
    }
    return isCorect;
  }

  
  @Override
  public String getName(){
    return name;
  }


  @Override
  public Order getOrder() {
    return order;
  }


  @Override
  public Timer getTimer() {
    return timer;
  }


  @Override
  public int getDifficulty() {
    return difficulty;
  }



  @Override
  public void setOrderResultCallback(Consumer<Boolean> callback) {
    this.orderResultCallback = callback;
  }
  
    
}
