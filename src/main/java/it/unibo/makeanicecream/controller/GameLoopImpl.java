package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Implementation of {@link GameLoop} that runs the game in a separate thread.
 */
public class GameLoopImpl implements GameLoop, Runnable{
    private static final double MILLIS_IN_SECONDS = 1000.0;

    private long period;
    private GameController controller;
    private boolean running;

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

		while(this.running){
			long currentCycleStartTime = System.currentTimeMillis();
			long elapsed = currentCycleStartTime - previousCycleStartTime;
            this.controller.updateGame(elapsed / MILLIS_IN_SECONDS);
			this.waitForNextFrame(currentCycleStartTime);
			previousCycleStartTime = currentCycleStartTime;
		}
    }

    protected void waitForNextFrame(long cycleStartTime){
		long dt = System.currentTimeMillis() - cycleStartTime;
		if (dt < period){
			try {
				Thread.sleep(period - dt);
			} catch (Exception ex){}
		}
	}
    
}
