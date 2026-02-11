package it.unibo.makeanicecream.engine;

import java.util.function.Consumer;

import it.unibo.makeanicecream.api.GameLoop;

/**
 * Implementation of {@link GameLoop} that runs the game in a separate thread.
 */
public final class GameLoopImpl implements GameLoop, Runnable {

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
            final long elapsed = currentCycleStartTime - previousCycleStartTime;

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
