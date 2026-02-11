package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Test class for the {@link PauseCommand} class.
 * 
 * <p>
 * Checks that the game is paused and the game loop is stopped.
 * </p>
 */
class PauseCommandTest {

    @Test
    void pausesGameAndStopsGameLoop() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final PauseCommand command = new PauseCommand(loop);

        command.execute(game);

        verify(game).pause();
        verify(loop).stop();
    }
}
