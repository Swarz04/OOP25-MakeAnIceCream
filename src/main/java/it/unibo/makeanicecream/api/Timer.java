package it.unibo.makeanicecream.api;

/**
 * Interface for a timer with a countdown functionality.
 * Implementations should provide mechanisms to start, pause, resume
 * also update the timer, as well as check its status.
 */
public interface Timer {
   /**
    * Starts the timer countdown.
    * After this method is called, the timer begins decreasing its time.
    */
   void start();

   /**
    * Pauses the timer countdown
    * The timer stops decreasing the remaining time until resumed.
    */
   void pause();

   /**
    * Resumes the timer countdown if it was paused
    */
   void resume();

   /**
    * Updates the timer state by the specified delta time
    * 
    * @param deltaTime the time that has passed since he last update (in seconds)
    */
   void update(double deltaTime);

   /**
    * Checks if the timer has expired (if it reaches zero)
    * 
    * @return true if the timer has expired; false otherwise
    */
   boolean isExpired();

   /**
    * Gets the remaining time in seconds
    * 
    * @return the remaining time in seconds (not negative)
    */ 
   double getTimeLeft();

   /**
    * Checks if the timer is currently paused.
    * 
    * @return true if the timer is paused, false otherwise.
    */
   boolean isPaused();

   /**
    * Sets a callback to be invoked when the timer expires.
    * The callback will be executed once when the timer reaches zero.
    * 
    * @param callback the Runnable to execut when timer expires.
    */
   void setOnExpired(Runnable callback);
}
