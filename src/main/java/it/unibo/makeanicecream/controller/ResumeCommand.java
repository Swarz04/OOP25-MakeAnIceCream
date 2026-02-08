package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

public class ResumeCommand implements Command{
    private Game game;
    private GameLoop gameLoop;

    public ResumeCommand(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;
    }

    @Override
    public void execute() {
        this.game.resume();
        this.gameLoop.start();
    }
}
