package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class StartLevelCommand implements Command{

    private Game game;
    //private final int levelNumber;

    public StartLevelCommand(final Game game) {
        this.game = game;
        //this.levelNumber = levelNumber;
    }

    @Override
    public void execute() {
        this.game.start(0);
    }
    
}
