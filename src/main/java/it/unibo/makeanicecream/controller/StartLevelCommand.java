package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

public class StartLevelCommand implements Command{

    private Game game;
    private GameLoop gameLoop;
    //private final int levelNumber;

    public StartLevelCommand(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;
        //this.levelNumber = levelNumber;
    }

    @Override
    public void execute() {
        this.game.start(0);

        if (!this.gameLoop.isRunning()) {
            this.gameLoop.start();
        }
    }
    
}
