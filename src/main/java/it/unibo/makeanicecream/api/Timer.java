package it.unibo.makeanicecream.api;

/**
 *
 */
public interface Timer {
          void start();
          void pause();
          void resume();
          void update(double deltaTime);

          boolean isExpired();         
          double getTimeLeft();
          boolean isPaused();
          
          void setOnExpired(Runnable callback);
}
