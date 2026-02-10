package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Ingredient;

public class AddIngredientCommand implements Command{

    private final Game game;
    private final String nameIng;

    public AddIngredientCommand(final Game game, final String nameIng) {
        this.game = game;
        this.nameIng = nameIng;
    }

    @Override
    public void execute() {
        final Ingredient ingredient = null;
        this.game.getPlayer().addIngredient(ingredient);
    }
    
}
