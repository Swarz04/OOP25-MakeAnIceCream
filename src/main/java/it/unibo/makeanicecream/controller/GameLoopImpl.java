package it.unibo.makeanicecream.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Implementation of {@link GameLoop} that runs the game in a separate thread.
 */
public final class GameLoopImpl implements GameLoop, Runnable {
    private static final double MILLIS_IN_SECONDS = 1000.0;

    private volatile boolean running;
    private final long period;

    /**
     * The game controller used to update the game state.
     * 
     * <p>
     * Note: the controller is passed from outside and may be mutable.
     * Modifications to the controller outside this class will affect the loop.
     * </p>
     */
    @SuppressFBWarnings("EI2")
    private final GameController controller;

    /**
     * Constructs a new game loop with the given controller and frame period.
     *
     * @param controller the game controller to update each frame
     * @param period the period of each frame in milliseconds
     */
    public GameLoopImpl(final GameController controller, final long period) {
        this.period = period;
        this.controller = controller;
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

            if (this.controller.isGamePlaying()) {
                this.controller.updateGame(elapsed / MILLIS_IN_SECONDS);
            }

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
