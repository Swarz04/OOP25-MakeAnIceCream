package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Command that pauses the game execution.
 * 
 * <p>
 * This command pauses the game state and stops the game loop.
 * </p>
 */
public final class PauseCommand implements Command {

    private final GameLoop gameLoop;

    /**
     * Constructs a new PauseCommand.
     *
     * @param gameLoop the game loop to be stopped
     */
    public PauseCommand(final GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    /**
     * Executes the command by pausing the game
     * and stopping the game loop.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.pause();
        this.gameLoop.stop();
    }
}
