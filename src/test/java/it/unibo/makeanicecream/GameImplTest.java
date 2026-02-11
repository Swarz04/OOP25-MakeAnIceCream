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

    @Test
    void testInitialStateIsMenu() {
        assertEquals(GameState.MENU, game.getState());
        assertNull(game.getCurrentLevel());
    }

    @Test
    void testStartLevelChangesStateToPlaying() {
        game.start(NUM_LEVEL);
        assertEquals(GameState.PLAYING, game.getState());
        assertNotNull(game.getCurrentLevel());
    }

    @Test
    void testStartWithInvalidLevelThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> game.start(INVALID_NUM_LEVEL_1));
        assertThrows(IllegalArgumentException.class, () -> game.start(INVALID_NUM_LEVEL_2));
    }

    @Test
    void testPauseAndResume() {
        game.start(NUM_LEVEL);

        game.pause();
        assertEquals(GameState.PAUSED, game.getState());

        game.resume();
        assertEquals(GameState.PLAYING, game.getState());
    }

    @Test
    void testReturnToMenu() {
        game.start(NUM_LEVEL);
        game.returnToMenu();
        assertEquals(GameState.MENU, game.getState());
        assertNull(game.getCurrentLevel());
    }

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

    @Test
    void testChooseCone() {
        game.start(NUM_LEVEL);
        assertTrue(game.chooseCone(Conetype.CLASSIC));
    }

    @Test
    void testAddIngredient() {
        game.start(NUM_LEVEL);
        game.chooseCone(Conetype.CLASSIC);
        assertTrue(game.addIngredient(IngredientFactory.createIngredient(INGREDIENT_NAME_1)));
    }

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

    @Test
    void testDeliverIceCreamWithNullCustomer() {
        game.start(NUM_LEVEL);
        final boolean delivered = game.deliverIceCream(null);
        assertFalse(delivered, "La consegna non dovrebbe riuscire se il cliente è null");
    }

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

    @Test
    void testUpdateDoesNothingWhenNoLevel() {
        // currentLevel == null
        game.update(DELTA_TIME);
        assertEquals(GameState.MENU, game.getState(), 
            "Lo stato non dovrebbe cambiare se non c'è nessun livello");
    }

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
