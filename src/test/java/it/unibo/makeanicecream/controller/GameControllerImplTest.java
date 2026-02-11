package it.unibo.makeanicecream.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameLoop;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.GameView;
import it.unibo.makeanicecream.api.Level;

/**
 * Test class for the {@link GameControllerImpl} class.
 * 
 * <p>
 * Verifies that the game controller correctly handles setting the view, processing input events,
 * querying game state, and updating the game and view.
 * </p>
 */
class GameControllerImplTest {
    private static final int NUM_LEVEL = 1;
    private static final double DELTA_TIME = 0.1;
    private static final String INGREDIENT_NAME = "VANILLA";
    private static final String CONE_TYPE = "CLASSIC";

    /**
     * Verifies that GameControllerImpl sets the controller in the view.
     */
    @Test
    void testSetView() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final GameControllerImpl controller = new GameControllerImpl(game, loop);
        final GameView view = mock(GameView.class);

        controller.setView(view);

        verify(view).setController(controller);
    }

    /**
     * Verifies that GameControllerImpl correctly adds an ingredient
     * to the game when receiving an ADD_INGREDIENT event.
     */
    @Test
    void testHandleInputAddIngredient() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final GameControllerImpl controller = new GameControllerImpl(game, loop);
        final Event event = mock(Event.class);
        when(event.getType()).thenReturn(EventType.ADD_INGREDIENT);
        when(event.getData()).thenReturn(INGREDIENT_NAME);

        controller.handleInput(event);

        verify(game).addIngredient(any());
    }

    /**
     * Verifies that GameControllerImpl correctly chooses a cone
     * when receiving a CHOOSE_CONE event.
     */
    @Test
    void testHandleInputChooseCone() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final GameControllerImpl controller = new GameControllerImpl(game, loop);
        final Event event = mock(Event.class);
        when(event.getType()).thenReturn(EventType.CHOOSE_CONE);
        when(event.getData()).thenReturn(CONE_TYPE);

        controller.handleInput(event);

        verify(game).chooseCone(any());
    }

    /**
     * Verifies that GameControllerImpl correctly starts the level
     * and starts the game loop, if it is not already running, when receiving a START_LEVEL event.
     */
    @Test
    void testHandleInputStartLevel() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final GameControllerImpl controller = new GameControllerImpl(game, loop);
        final Event event = mock(Event.class);

        when(loop.isRunning()).thenReturn(false);
        when(event.getType()).thenReturn(EventType.START_LEVEL);
        when(event.getData()).thenReturn("1");

        controller.handleInput(event);

        verify(game).start(NUM_LEVEL);
        verify(loop).start();
    }

    /**
     * Verifies that GameControllerImpl returns the current game playing state.
     */
    @Test
    void testIsGamePlaying() {
        final Game game = mock(Game.class);
        final GameControllerImpl controller = new GameControllerImpl(game, mock(GameLoop.class));

        when(game.isPlaying()).thenReturn(true);

        assertTrue(controller.isGamePlaying());
    }

    /**
     * Verifies that GameControllerImpl returns the correct game state.
     */
    @Test
    void testGetGameState() {
        final Game game = mock(Game.class);
        final GameState state = GameState.PLAYING;
        final GameControllerImpl controller = new GameControllerImpl(game, mock(GameLoop.class));

        when(game.getState()).thenReturn(state);

        assertEquals(state, controller.getGameState());
    }

    /**
     * Verifies that GameControllerImpl stops the game loop
     * if the game is not playing but the loop is running.
     */
    @Test
    void testUpdateGameStopsLoopIfNotPlaying() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final GameControllerImpl controller = new GameControllerImpl(game, loop);

        when(game.isPlaying()).thenReturn(false);
        when(loop.isRunning()).thenReturn(true);

        controller.updateGame(DELTA_TIME);

        verify(game).update(DELTA_TIME);
        verify(loop).stop();
    }

    /**
     * Verifies that GameControllerImpl updates the view
     * when the game is playing and a current level is present.
     */
    @Test
    void testUpdateGameUpdatesViewWhenPlaying() {
        final Game game = mock(Game.class);
        final GameLoop loop = mock(GameLoop.class);
        final GameControllerImpl controller = new GameControllerImpl(game, loop);
        final GameView view = mock(GameView.class);

        controller.setView(view);

        when(game.isPlaying()).thenReturn(true);
        when(game.getCurrentLevel()).thenReturn(mock(Level.class));

        controller.updateGame(DELTA_TIME);

        verify(game).update(DELTA_TIME);
        verify(view).showLives(anyInt());
    }
}
