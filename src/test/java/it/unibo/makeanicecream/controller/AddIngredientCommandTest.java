package it.unibo.makeanicecream.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Ingredient;

/**
 * Unit test for {@link AddIngredientCommand}.
 * 
 * <p>
 * This test verifies that the AddIngredientCommand correctly creates
 * an ingredient and adds it to the provided {@link Game} instance when executed.
 * </p>
 */
public class AddIngredientCommandTest {
    private static final String INGREDIENT_NAME = "VANILLA";

    @Test
    void createIngredientAndAddItToGame() {
        Game game = mock(Game.class);
        final AddIngredientCommand command = new AddIngredientCommand(INGREDIENT_NAME);
        command.execute(game);
        verify(game).addIngredient(any(Ingredient.class));
    }
}
