package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class AddIngredientCommand implements Command{

    private Game game;

    public AddIngredientCommand(final Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        /*Da sistemare una volta corretto composeIceCream(). */
        //this.game.getPlayer().composeIceCream(null);
    }
    
}
