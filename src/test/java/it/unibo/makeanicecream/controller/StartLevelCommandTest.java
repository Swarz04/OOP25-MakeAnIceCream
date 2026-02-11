package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Test class for the {@link StartLevelCommand} class.
 * 
 * <p>
 * Checks that the specified level is started and the game loop is started if not already running.
 * </p>
 */
class StartLevelCommandTest {
    private static final int NUM_LEVEL_1 = 2;
    private static final int NUM_LEVEL_2 = 3;

    @Test
    void startsLevelAndGameLoop() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);

        // Simulate game loop not running initially
        when(loop.isRunning()).thenReturn(false);

        final StartLevelCommand command = new StartLevelCommand(loop, NUM_LEVEL_1);
        command.execute(game);

        verify(game).start(NUM_LEVEL_1);
        verify(loop).start();
    }

    @Test
    void startsLevelWithoutRestartingLoop() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);

        // Simulate game loop already running
        when(loop.isRunning()).thenReturn(true);

        final StartLevelCommand command = new StartLevelCommand(loop, NUM_LEVEL_2);
        command.execute(game);

        verify(game).start(NUM_LEVEL_2);
        // Verify that start is not called on a running loop
        verify(loop, never()).start();
        verify(loop).isRunning(); // confirm we checked the running state
    }
}
