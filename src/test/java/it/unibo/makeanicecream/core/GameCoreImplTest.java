package it.unibo.makeanicecream.core;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Test class for the {@link GameCoreImpl} class.
 */
class GameCoreImplTest {

    private GameController controller;
    private GameCoreImpl core;

    @BeforeEach
    void setUp() {
        final Game game = mock(Game.class);
        controller = mock(GameController.class);
        final GameLoop loop = mock(GameLoop.class);
        core = new GameCoreImpl(game, controller, loop);
    }

    /**
     * Tests that GameCoreImpl returns the same
     * GameController instance that was injected via the constructor.
     */
    @Test
    void testGetController() {
        assertSame(controller, core.getController());
    }
}
