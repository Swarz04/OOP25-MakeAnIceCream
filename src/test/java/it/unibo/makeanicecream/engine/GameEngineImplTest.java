package it.unibo.makeanicecream.engine;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;

/**
 * Test class for the {@link GameEngineImpl} class.
 */
class GameEngineImplTest {

    private GameController controller;
    private GameLoop loop;
    private GameEngineImpl engine;

    @BeforeEach
    void setUp() {
        final Game game = mock(Game.class);
        controller = mock(GameController.class);
        loop = mock(GameLoop.class);
        engine = new GameEngineImpl(game, controller, loop);
    }

    /**
     * Tests that calling GameEngineImpl correctly delegates
     * the start operation to the GameLoop.
     */
    @Test
    void testStartShouldCallLoopStart() {
        engine.start();
        verify(loop).start();
    }

    /**
     * Tests that calling GameEngineImpl correctly delegates
     * the stop operation to the GameLoop.
     */
    @Test
    void testStopShouldCallLoopStop() {
        engine.stop();
        verify(loop).stop();
    }

    /**
     * Tests that GameEngineImpl returns the same
     * GameController instance that was injected via the constructor.
     */
    @Test
    void testGetController() {
        assertSame(controller, engine.getController());
    }
}
