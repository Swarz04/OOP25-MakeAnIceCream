package it.unibo.makeanicecream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.Level;
import it.unibo.makeanicecream.model.GameImpl;
import it.unibo.makeanicecream.model.IngredientFactory;

/**
 * Unit tests for {@link GameImpl}.
 * This test suite verifies:
 * <ul>
 *     <li>Initial game state</li>
 *     <li>State transitions (MENU, PLAYING, PAUSED, GAME_OVER, LEVEL_COMPLETED)</li>
 *     <li>Player actions delegation (cone selection, ingredient addition, delivery, cancel)</li>
 *     <li>Update logic and level progress checks</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
class GameImplTest {
    private static final int NUM_LEVEL = 1;
    private static final int INVALID_NUM_LEVEL_1 = 0;
    private static final double DELTA_TIME = 0.1;
    private static final int INVALID_NUM_LEVEL_2 = -5;
    private static final String INGREDIENT_NAME_1 = "VANILLA";
    private static final String INGREDIENT_NAME_2 = "CHOCOLATE";

    private GameImpl game;
    @Mock
    private Customer mockCustomer;

    @BeforeEach
    void setUp() {
        game = new GameImpl();
    }

    /**
     * Verifies that a newly created game starts in MENU state
     * and has no current level.
     */
    @Test
    void testInitialStateIsMenu() {
        assertEquals(GameState.MENU, game.getState());
        assertNull(game.getCurrentLevel());
    }

    /**
     * Verifies that starting a valid level switches the state to PLAYING
     * and initializes the current level.
     */
    @Test
    void testStartLevelChangesStateToPlaying() {
        game.start(NUM_LEVEL);
        assertEquals(GameState.PLAYING, game.getState());
        assertNotNull(game.getCurrentLevel());
    }

    /**
     * Verifies that starting the game with an invalid level number
     * throws an IllegalArgumentException.
     */
    @Test
    void testStartWithInvalidLevelThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> game.start(INVALID_NUM_LEVEL_1));
        assertThrows(IllegalArgumentException.class, () -> game.start(INVALID_NUM_LEVEL_2));
    }

    /**
     * Verifies correct state transitions when pausing and resuming the game.
     */
    @Test
    void testPauseAndResume() {
        game.start(NUM_LEVEL);

        game.pause();
        assertEquals(GameState.PAUSED, game.getState());

        game.resume();
        assertEquals(GameState.PLAYING, game.getState());
    }

    /**
     * Verifies that returning to the menu resets the game state
     * and clears the current level.
     */
    @Test
    void testReturnToMenu() {
        game.start(NUM_LEVEL);
        game.returnToMenu();
        assertEquals(GameState.MENU, game.getState());
        assertNull(game.getCurrentLevel());
    }

    /**
     * Verifies that the game enters GAME_OVER state when all lives are exhausted
     * after an update.
     */
    @Test
    void testGameOverWhenLivesExhausted() {
        game.start(NUM_LEVEL);
        final Level level = game.getCurrentLevel();
        while (level.getLives() > 0) {
            level.loseLife();
        }
        game.update(DELTA_TIME);
        assertEquals(GameState.GAME_OVER, game.getState(), "Il gioco dovrebbe andare in GAME_OVER se finiscono le vite");
    }

    /**
     * Verifies that the game enters LEVEL_COMPLETED state
     * when all customers have been served.
     */
    @Test
    void testLevelCompletedWhenNoCustomersLeft() {
        game.start(NUM_LEVEL);
        final Level level = game.getCurrentLevel();
        while (level.hasNextCustomer()) {
            level.serveCurrentCustomer();
        }
        game.update(DELTA_TIME);
        assertEquals(GameState.LEVEL_COMPLETED, game.getState(),
        "Il gioco dovrebbe andare in LEVEL_COMPLETED se finiscono i clienti");
    }

    /**
     * Verifies that selecting a cone while playing returns true.
     */
    @Test
    void testChooseCone() {
        game.start(NUM_LEVEL);
        assertTrue(game.chooseCone(Conetype.CLASSIC));
    }

    /**
     * Verifies that adding a valid ingredient after selecting a cone succeeds.
     */
    @Test
    void testAddIngredient() {
        game.start(NUM_LEVEL);
        game.chooseCone(Conetype.CLASSIC);
        assertTrue(game.addIngredient(IngredientFactory.createIngredient(INGREDIENT_NAME_1)));
    }

    /**
     * Verifies successful ice cream delivery when the customer accepts it.
     * Also checks that the customer's receiveIceCream method is invoked.
     */
    @Test
    void testDeliverIceCreamSuccess() {
        when(mockCustomer.receiveIceCream(any())).thenReturn(true);

        game.start(NUM_LEVEL);
        game.chooseCone(Conetype.CLASSIC);
        game.addIngredient(IngredientFactory.createIngredient(INGREDIENT_NAME_1));

        final boolean delivered = game.deliverIceCream(mockCustomer);
        assertTrue(delivered, "La consegna dovrebbe riuscire se il cliente riceve il gelato");

        verify(mockCustomer).receiveIceCream(any());
    }

    /**
     * Verifies that delivering an ice cream to a null customer fails.
     */
    @Test
    void testDeliverIceCreamWithNullCustomer() {
        game.start(NUM_LEVEL);
        final boolean delivered = game.deliverIceCream(null);
        assertFalse(delivered, "La consegna non dovrebbe riuscire se il cliente è null");
    }

    /**
     * Verifies that canceling the current ice cream resets the builder,
     * allowing a new ice cream to be created and delivered successfully.
     */
    @Test
    void testCancelIceCreamResetsBuilder() {
        when(mockCustomer.receiveIceCream(any())).thenReturn(true);

        game.start(NUM_LEVEL);
        game.chooseCone(Conetype.CLASSIC);
        game.addIngredient(IngredientFactory.createIngredient(INGREDIENT_NAME_1));
        game.cancelIceCream();

        game.chooseCone(Conetype.CLASSIC);
        game.addIngredient(IngredientFactory.createIngredient(INGREDIENT_NAME_2));
        final boolean delivered = game.deliverIceCream(mockCustomer);
        assertTrue(delivered, "La consegna successiva dovrebbe funzionare con un nuovo gelato");
        verify(mockCustomer).receiveIceCream(any());
    }

    /**
     * Verifies that calling update when no level is active
     * does not change the game state.
     */
    @Test
    void testUpdateDoesNothingWhenNoLevel() {
        // currentLevel == null
        game.update(DELTA_TIME);
        assertEquals(GameState.MENU, game.getState(), 
            "Lo stato non dovrebbe cambiare se non c'è nessun livello");
    }

    /**
     * Verifies that calling update while the game is paused
     * does not modify the game state or the level lives.
     */
    @Test
    void testUpdateDoesNothingWhenPaused() {
        game.start(NUM_LEVEL);
        game.pause();
        final Level level = game.getCurrentLevel();
        final int livesBefore = level.getLives();

        game.update(DELTA_TIME);
        assertEquals(GameState.PAUSED, game.getState(),
            "Lo stato non dovrebbe cambiare se il gioco è in pausa");
        assertEquals(livesBefore, level.getLives(),
            "Le vite non dovrebbero cambiare se il gioco è in pausa");
    }

    /**
     * Verifies that update keeps the game in PLAYING state
     * when there are still customers and lives available.
     */
    @Test
    void testUpdateRemainsPlayingWithCustomersAndLives() {
        game.start(NUM_LEVEL);
        final Level level = game.getCurrentLevel();

        assertTrue(level.hasNextCustomer(), "Il livello deve avere clienti");
        assertTrue(level.getLives() > 0, "Il livello deve avere vite");

        game.update(DELTA_TIME);
        assertEquals(GameState.PLAYING, game.getState(),
            "Il gioco dovrebbe rimanere PLAYING se ci sono clienti e vite disponibili");
    }
}
