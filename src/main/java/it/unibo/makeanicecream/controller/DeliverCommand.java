package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class DeliverCommand implements Command{

    private Game game;

    public DeliverCommand(final Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        this.game.getPlayer().deliverIceCream(this.game.getCurrentLevel().getCurrentCustomer());
    }
    
}
