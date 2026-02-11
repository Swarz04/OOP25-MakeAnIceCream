package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Test class for the {@link ResumeCommand} class.
 * 
 * <p>
 * Checks that the game is resumed and the game loop is restarted.
 * </p>
 */
class ResumeCommandTest {

    @Test
    void pausesGameAndStopsGameLoop() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final ResumeCommand command = new ResumeCommand(loop);

        command.execute(game);

        verify(game).resume();
        verify(loop).start();
    }
}
