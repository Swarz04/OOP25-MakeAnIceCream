package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class ChooseConeCommand implements Command{

    private final Game game;
    //private final ConeType cone;

    public ChooseConeCommand(final Game game) {
        this.game = game;
        //this.cone = cone;
    }

    @Override
    public void execute() {
        
    }
    
}
