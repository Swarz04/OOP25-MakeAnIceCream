package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Game;

/**
 * Unit test for {@link ChooseConeCommand}.
 * 
 * <p>
 * This test verifies that executing the ChooseConeCommand correctly
 * selects the specified cone type on the provided Game instance.
 * </p>
 */
class ChooseConeCommandTest {

    @Test
    void shouldChooseTheCorrectCone() {
        final Game game = mock(Game.class);
        final Conetype selectedCone = Conetype.CLASSIC;
        final ChooseConeCommand command = new ChooseConeCommand(selectedCone);

        command.execute(game);
        verify(game).chooseCone(selectedCone);
    }
}
