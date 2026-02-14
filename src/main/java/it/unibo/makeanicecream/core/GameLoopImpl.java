package it.unibo.makeanicecream.core;

import java.util.function.Consumer;

import it.unibo.makeanicecream.api.GameLoop;

/**
 * Implementation of {@link GameLoop} that runs the game in a separate thread.
 */
public final class GameLoopImpl implements GameLoop, Runnable {
    private static final long MIN_ELAPSED = 0;
    private static final long MAX_ELAPSED = 100;

    private volatile boolean running;
    private final long period;
    private final Consumer<Long> updater;

    /**
     * Constructs a new game loop with the given controller and frame period.
     *
     * @param period the period of each frame in milliseconds
     * @param updater the action to be executed at each frame iteration
     */
    public GameLoopImpl(final long period, final Consumer<Long> updater) {
        this.period = period;
        this.updater = updater;
        this.running = false;
    }

    @Override
    public void start() {
        this.running = true;
        new Thread(this).start();
    }

    @Override
    public void stop() {
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public void run() {
        long previousCycleStartTime = System.currentTimeMillis();

        while (this.running) {
            final long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            elapsed = Math.max(MIN_ELAPSED, elapsed);
            elapsed = Math.min(elapsed, MAX_ELAPSED);

            this.updater.accept(elapsed);

            this.waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    /**
     * Waits until the next frame, sleeping if necessary.
     *
     * @param cycleStartTime the timestamp at the start of the current frame
     */
    private void waitForNextFrame(final long cycleStartTime) {
        final long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (final InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
