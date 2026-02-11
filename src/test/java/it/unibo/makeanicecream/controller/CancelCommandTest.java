package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;

/**
 * Test class for the {@link CancelCommand} class.
 * 
 * <p>
 * Verifies that the current ice cream is cancelled in the game.
 * </p>
 */
class CancelCommandTest {

    @Test
    void cancelCurrentIceCream() {
        final Game game = mock(Game.class);
        final CancelCommand command = new CancelCommand();

        command.execute(game);
        verify(game).cancelIceCream();
    }
}
