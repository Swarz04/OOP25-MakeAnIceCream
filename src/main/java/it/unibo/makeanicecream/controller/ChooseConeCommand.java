package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Game;

public class ChooseConeCommand implements Command{

    private final Game game;
    private final Conetype cone;

    public ChooseConeCommand(final Game game, final Conetype cone) {
        this.game = game;
        this.cone = cone;
    }

    @Override
    public void execute() {
        game.getPlayer().chooseCone(this.cone);
    }
    
}
