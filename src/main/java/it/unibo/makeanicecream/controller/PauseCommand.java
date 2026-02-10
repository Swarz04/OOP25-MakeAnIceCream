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

    private final Game game;
    private final GameLoop gameLoop;

    /**
     * Constructs a new PauseCommand.
     *
     * @param game the game instance to be paused
     * @param gameLoop the game loop to be stopped
     */
    public PauseCommand(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;
    }

    /**
     * Executes the command by pausing the game
     * and stopping the game loop.
     */
    @Override
    public void execute() {
        this.game.pause();
        this.gameLoop.stop();
    }
}
