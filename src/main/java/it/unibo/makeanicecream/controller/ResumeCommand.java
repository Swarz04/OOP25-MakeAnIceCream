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

    private final GameLoop gameLoop;

    /**
     * Constructs a new ResumeCommand.
     *
     * @param gameLoop the game loop to be restarted
     */
    public ResumeCommand(final GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    /**
     * Executes the command by resuming the game
     * and restarting the game loop.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.resume();
        this.gameLoop.start();
    }
}
