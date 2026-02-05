package it.unibo.makeanicecream.api.Customer;

/**
 *
 */
public interface Timer {
          void start();
          void pause();
          void resume();
          boolean isExpired();         
          double getTimeLeft();
          void setOnExpired();
}
