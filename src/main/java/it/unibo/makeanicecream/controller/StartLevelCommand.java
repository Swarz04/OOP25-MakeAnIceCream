package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Command that starts a specific level of the game.
 * 
 * <p>
 * This command initializes the selected level and starts
 * the game loop if it is not already running.
 * </p>
 */
public final class StartLevelCommand implements Command {

    private final GameLoop gameLoop;
    private final int levelNumber;

    /**
     * Constructs a new StartLevelCommand.
     *
     * @param gameLoop the game loop to be started if not already running
     * @param levelNumber the number of the level to start
     */
    public StartLevelCommand(final GameLoop gameLoop, final int levelNumber) {
        this.gameLoop = gameLoop;
        this.levelNumber = levelNumber;
    }

    /**
     * Executes the command by starting the specified level
     * and ensuring that the game loop is running.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.start(this.levelNumber);

        if (!this.gameLoop.isRunning()) {
            this.gameLoop.start();
        }
    }
}
