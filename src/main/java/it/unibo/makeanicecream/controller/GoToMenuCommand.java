package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Command that returns the game to the main menu.
 * 
 * <p>
 * This command stops the game loop and resets the game state
 * to the main menu.
 * </p>
 */
public final class GoToMenuCommand implements Command {

    private final Game game;
    private final GameLoop gameLoop;

    /**
     * Constructs a new GoToMenuCommand.
     *
     * @param game the game instance to return to the menu
     * @param gameLoop the game loop to be stopped
     */
    public GoToMenuCommand(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;
    }

    /**
     * Executes the command by stopping the game loop
     * and returning the game to the main menu.
     */
    @Override
    public void execute() {
        this.game.returnToMenu();
        this.gameLoop.stop();
    }
}
