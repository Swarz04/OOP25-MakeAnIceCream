package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.model.IngredientFactory;

public class AddIngredientCommand implements Command{

    private final Game game;
    private final String nameIng;

    public AddIngredientCommand(final Game game, final String nameIng) {
        this.game = game;
        this.nameIng = nameIng;
    }

    @Override
    public void execute() {
        final Ingredient ingredient = IngredientFactory.createIngredient(this.nameIng);
        this.game.getPlayer().addIngredient(ingredient);
    }
    
}
