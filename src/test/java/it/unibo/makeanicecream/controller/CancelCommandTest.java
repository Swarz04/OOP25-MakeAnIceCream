package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;

/**
 * Unit test for {@link CancelCommand}.
 * 
 * <p>
 * This test verifies that executing the CancelCommand correctly
 * cancels the current ice cream on the provided Game instance.
 * </p>
 */
class CancelCommandTest {

    @Test
    void shouldCancelCurrentIceCream() {
        final Game game = mock(Game.class);
        final CancelCommand command = new CancelCommand();

        command.execute(game);
        verify(game).cancelIceCream();
    }
}
