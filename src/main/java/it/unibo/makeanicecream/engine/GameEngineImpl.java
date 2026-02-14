package it.unibo.makeanicecream.engine;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameEngine;
import it.unibo.makeanicecream.api.GameLoop;
import it.unibo.makeanicecream.controller.GameControllerImpl;
import it.unibo.makeanicecream.model.GameImpl;

/**
 * Implementation of {@link GameEngine}.
 */
public final class GameEngineImpl implements GameEngine {

    private static final long PERIOD = 16;
    private static final double MILLIS_IN_SECONDS = 1000.0;

    private final Game game;
    private final GameController controller;
    private final GameLoop loop;

    /**
     * Constructs a new game engine and initializes
     * all core components of the game.
     */
    public GameEngineImpl() {
        this.game = new GameImpl();
        this.loop = new GameLoopImpl(PERIOD, this::update);
        this.controller = new GameControllerImpl(game, this.loop);
    }

    /**
     * Updates the game state for the current frame.
     * 
     * <p>
     * This method is invoked by the {@link GameLoop} at each iteration.
     * It forwards the elapsed time (converted to seconds) to the controller
     * only if the game is currently in the playing state.
     * </p>
     *
     * @param elapsedMillis the time elapsed since the previous frame, in milliseconds
     */
    private void update(final long elapsedMillis) {
        if (controller.isGamePlaying()) {
            controller.updateGame(elapsedMillis / MILLIS_IN_SECONDS);
        }
    }

    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "Controller is meant to be shared with the view."
    )
    @Override
    public GameController getController() {
        return controller;
    }
}
