package it.unibo.makeanicecream;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameEngine;
import it.unibo.makeanicecream.api.GameLoop;
import it.unibo.makeanicecream.controller.GameControllerImpl;
import it.unibo.makeanicecream.controller.GameLoopImpl;
import it.unibo.makeanicecream.model.GameImpl;

public class GameEngineImpl implements GameEngine{

    private static final long PERIOD = 16; // ~60 FPS

    private final Game game;
    private final GameController controller;
    private final GameLoop loop;

    /**
     * Costruisce e collega i componenti principali del gioco.
     */
    public GameEngineImpl() {
        this.game = new GameImpl();
        this.loop = new GameLoopImpl(PERIOD, elapsedMillis -> update(elapsedMillis));
        this.controller = new GameControllerImpl(game, this.loop);
    }

    /**
     * Metodo chiamato dal GameLoop ad ogni frame.
     *
     * @param elapsedMillis millisecondi trascorsi dall'ultimo frame
     */
    private void update(final long elapsedMillis) {
        if (controller.isGamePlaying()) {
            controller.updateGame(elapsedMillis / 1000.0); // deltaTime in secondi
        }
    }

    /**
     * Ritorna il controller, utile per collegare la view.
     */
    @Override
    public GameController getController() {
        return controller;
    }

    /**
     * Avvia il GameLoop.
     */
    @Override
    public void start() {
        loop.start();
    }

    /**
     * Ferma il GameLoop.
     */
    @Override
    public void stop() {
        loop.stop();
    }
}
