package it.unibo.makeanicecream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.model.GameImpl;

class GameImplTest {

    private GameImpl game;

    @BeforeEach
    void setUp() {
        game = new GameImpl();
    }

    @Test
    void testInitialStateIsMenu() {
        assertEquals(GameState.MENU, game.getState());
        assertNull(game.getCurrentLevel());
    }

    @Test
    void testStartLevelChangesStateToPlaying() {
        game.start(1);

        assertEquals(GameState.PLAYING, game.getState());
        assertNotNull(game.getCurrentLevel());
    }

    @Test
    void testStartWithInvalidLevelThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.start(0);
        });
    }

    @Test
    void testPauseAndResume() {
        game.start(1);

        game.pause();
        assertEquals(GameState.PAUSED, game.getState());

        game.resume();
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testReturnToMenu() {
        game.start(1);

        game.returnToMenu();

        assertEquals(GameState.MENU, game.getState());
        assertNull(game.getCurrentLevel());
    }
}
