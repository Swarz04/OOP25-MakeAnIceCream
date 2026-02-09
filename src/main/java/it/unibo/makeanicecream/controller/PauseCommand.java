package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class PauseCommand implements Command{

    private Game game;

    public PauseCommand(final Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        this.game.pause();
    }
    
}
