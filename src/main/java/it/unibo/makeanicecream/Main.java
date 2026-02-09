package it.unibo.makeanicecream;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameView;
import it.unibo.makeanicecream.controller.GameControllerImpl;
import it.unibo.makeanicecream.model.GameImpl;
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
     * @param args The command line arguments.
     */
    public static void main(final String[] args) {
        final Game model = new GameImpl();
        final GameController controller = new GameControllerImpl(model);
        final GameView view = new GameViewImpl(model);
        controller.setView(view);
        view.start();
    }
}
