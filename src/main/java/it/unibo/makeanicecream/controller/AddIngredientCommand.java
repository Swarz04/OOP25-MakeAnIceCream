package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class AddIngredientCommand implements Command{

    private final Game game;
    //private final Ingredient ingredient;

    public AddIngredientCommand(final Game game) {
        this.game = game;
        //this.ingredient = ingredient;
    }

    @Override
    public void execute() {
        /*Da sistemare una volta corretto composeIceCream(). */
        //this.game.getPlayer().composeIceCream(this.ingredient);
    }
    
}
