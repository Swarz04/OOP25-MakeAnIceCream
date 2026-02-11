package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Game;

/**
 * Test class for the {@link ChooseConeCommand} class.
 * 
 * <p>
 * Verifies that the selected cone is set in the game.
 * </p>
 */
class ChooseConeCommandTest {

    @Test
    void chooseTheCorrectCone() {
        final Game game = mock(Game.class);
        final Conetype selectedCone = Conetype.CLASSIC;
        final ChooseConeCommand command = new ChooseConeCommand(selectedCone);

        command.execute(game);
        verify(game).chooseCone(selectedCone);
    }
}
