package it.unibo.makeanicecream.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.model.IngredientFactory;

/**
 * Test class for the {@link AddIngredientCommand} class.
 * 
 * <p>
 * Verifies that an ingredient is created and added to the
 * provided game instance.
 * Note: This test does not check the internal logic of the {@link IngredientFactory},
 * as the factory is assumed to work correctly. The focus is on the interaction between
 * the command and the game.
 * </p>
 */
class AddIngredientCommandTest {
    private static final String INGREDIENT_NAME = "VANILLA";

    @Test
    void createIngredientAndAddItToGame() {
        final Game game = mock(Game.class);
        final AddIngredientCommand command = new AddIngredientCommand(INGREDIENT_NAME);

        command.execute(game);
        verify(game).addIngredient(any(Ingredient.class));
    }
}
