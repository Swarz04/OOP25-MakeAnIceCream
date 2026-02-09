package it.unibo.makeanicecream.model.customermodel;

import it.unibo.makeanicecream.api.Timer;

/**
 * Implementation of the Timer interface for costumer orders.
 * This class manages a countdown timer that can be started, paused, resumed
 * and notifies when expired via a callback.
 *
 */
public final class CustomerTimer implements Timer {

    private double secondsLeft;
    private boolean expired;
    private boolean paused = true;
    private Runnable onExpiredCallback;

    /**
     * Constructor with the specified duration of the timer.
     * The timer starts in paused state.
     *
     * @param seconds the initial time must be in positive seconds.
     */
    public CustomerTimer(final double seconds) {
       if (seconds <= 0) {
            throw new IllegalArgumentException("Il tempo deve essere positivo");
        }
        this.secondsLeft = seconds;
    }

    /**
     * Starts the timer.
     * The timer will begin counting down. 
     */
    @Override
    public void start() {
        this.paused = false;
    }

    /**
     * Pauses the timer.
     * The timer will stop counting down.
     */
    @Override
    public void pause() {
        this.paused = true;
    }

    /**
     * Resumes the timer if it was paused.
     */
    @Override
    public void resume() {
        this.paused = false;
    }

    /**
     * Updates the timer state by the specified delta time.
     * If the timer reaches zero, the expired callback will be invoked.
     *
     * @param deltaTime the time passed since the last update (in seconds).
     */
    @Override
    public void update(final double deltaTime) {
        if (expired || paused) {
            return;
        }

        secondsLeft -= deltaTime;

        if (secondsLeft <= 0) {
            expired = true;
            secondsLeft = 0;
            if (onExpiredCallback != null) {
                onExpiredCallback.run();
            }
        }
    }

    /**
     * Check if the timer has expired.
     *
     * @return true if the timer has reached zero, false otherwise.
     */
    @Override
    public boolean isExpired() {
       return expired;
    }

    /**
     * Gets the remaining time in seconds.
     *
     * @return the remaining time in seconds (not negative).
     */
    @Override
    public double getTimeLeft() {
       return Math.max(0, secondsLeft);
    }

    /**
     * Check if the timer is currently paused.
     *
     * @return true if the timer is paused, false otherwise.
     */
    @Override
    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets a callback to be invoked when the timer expires.
     * The callback will be executed once when the timer reaches zero.
     */
    @Override
    public void setOnExpired(final Runnable callback) { 
        this.onExpiredCallback = callback;
    }
}
