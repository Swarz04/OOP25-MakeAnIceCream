package it.unibo.makeanicecream;

import it.unibo.makeanicecream.api.GameEngine;
import it.unibo.makeanicecream.api.GameView;
import it.unibo.makeanicecream.engine.GameEngineImpl;
import it.unibo.makeanicecream.view.GameViewImpl;

/**
 * Main class to start the "Make an Ice Cream" game.
 */
public final class Main {

    private Main() {
        // This class should not be instantiated.
    }

    /**
     * The main entry point of the application.
     * 
     * @param args The command line arguments.
     */
    public static void main(final String[] args) {
        final GameEngine engine = new GameEngineImpl();
        final GameView view = new GameViewImpl();
        engine.getController().setView(view);
        view.start();

        view.showCustomer("Mario");
        view.showOrder("Cioccolato\nCono\nCookies");
        view.showTimer(45);
        view.showLives(3);
    }
}
