package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Command that resumes the game execution.
 * 
 * <p>
 * This command resumes the game state and restarts the game loop.
 * </p>
 */
public final class ResumeCommand implements Command {

    private final Game game;
    private final GameLoop gameLoop;

    /**
     * Constructs a new ResumeCommand.
     *
     * @param game the game instance to be resumed
     * @param gameLoop the game loop to be restarted
     */
    public ResumeCommand(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;
    }

    /**
     * Executes the command by resuming the game
     * and restarting the game loop.
     */
    @Override
    public void execute() {
        this.game.resume();
        this.gameLoop.start();
    }
}
