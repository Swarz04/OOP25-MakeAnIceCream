package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Ingredient;

public class AddIngredientCommand implements Command{

    private final Game game;
    private final Ingredient ingredient;

    public AddIngredientCommand(final Game game, final Ingredient ingredient) {
        this.game = game;
        this.ingredient = ingredient;
    }

    @Override
    public void execute() {
        this.game.getPlayer().addIngredient(this.ingredient);
    }
    
}
