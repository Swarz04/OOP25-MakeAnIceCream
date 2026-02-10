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

    private final GameLoop gameLoop;

    /**
     * Constructs a new GoToMenuCommand.
     *
     * @param gameLoop the game loop to be stopped
     */
    public GoToMenuCommand(final GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    /**
     * Executes the command by stopping the game loop
     * and returning the game to the main menu.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.returnToMenu();
        this.gameLoop.stop();
    }
}
