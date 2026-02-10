package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.model.IngredientFactory;

/**
 * Command that adds an ingredient to the current player's ice cream.
 * 
 * <p>
 * This command is executed by the game controller when the user
 * selects an ingredient to add to the ice cream.
 * </p>
 */
public final class AddIngredientCommand implements Command {

    private final String nameIng;

    /**
     * Constructs a new AddIngredientCommand.
     *
     * @param nameIng the name of the ingredient to add
     */
    public AddIngredientCommand(final String nameIng) {
        this.nameIng = nameIng;
    }

    /**
     * Executes the command by creating the ingredient and adding it
     * to the player's ice cream.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        final Ingredient ingredient = IngredientFactory.createIngredient(this.nameIng);
        game.addIngredient(ingredient);
    }
}
