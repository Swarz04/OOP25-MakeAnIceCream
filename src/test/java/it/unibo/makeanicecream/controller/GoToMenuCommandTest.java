package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Test class for the {@link GoToMenuCommand} class.
 * 
 * <p>
 * Verifies that executing the command stops the game loop
 * and returns the game to the main menu.
 * </p>
 */
class GoToMenuCommandTest {

    @Test
    void returnToMenuAndStopGameLoop() {
        final Game game = mock(Game.class);
        final GameLoop gameLoop = mock(GameLoop.class);
        final GoToMenuCommand command = new GoToMenuCommand(gameLoop);

        command.execute(game);

        verify(game).returnToMenu();
        verify(gameLoop).stop();
    }
}
