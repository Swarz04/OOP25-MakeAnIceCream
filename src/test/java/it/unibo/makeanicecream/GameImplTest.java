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
        game.start(1);

        assertEquals(GameState.PLAYING, game.getState());
        assertNotNull(game.getCurrentLevel());
    }

    @Test
    void testStartWithInvalidLevelThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> game.start(0));
        assertThrows(IllegalArgumentException.class, () -> game.start(-5));
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

    @Test
    void testGameOverWhenLivesExhausted() {
        game.start(1);
        Level level = game.getCurrentLevel();
        while (level.getLives() > 0) {
            level.loseLife();
        }
        game.update(0.1); // forza il check dello stato
        assertEquals(GameState.GAME_OVER, game.getState(), "Il gioco dovrebbe andare in GAME_OVER se finiscono le vite");
    }

    @Test
    void testLevelCompletedWhenNoCustomersLeft() {
        game.start(1);
        Level level = game.getCurrentLevel();
        while (level.hasNextCustomer()) {
            level.serveCurrentCustomer(); // consuma tutti i clienti
        }
        game.update(0.1);
        assertEquals(GameState.LEVEL_COMPLETED, game.getState(), "Il gioco dovrebbe andare in LEVEL_COMPLETED se finiscono i clienti");
    }

    @Test
    void testChooseCone() {
        game.start(1);
        assertTrue(game.chooseCone(Conetype.CLASSIC));
    }

    @Test
    void testAddIngredient() {
        game.start(1);
        game.chooseCone(Conetype.CLASSIC);
        assertTrue(game.addIngredient(IngredientFactory.createIngredient("VANILLA")));
    }

    @Test
    void testDeliverIceCreamSuccess() {
        when(mockCustomer.receiveIceCream(any())).thenReturn(true);

        game.start(1);
        game.chooseCone(Conetype.CLASSIC);
        game.addIngredient(IngredientFactory.createIngredient("VANILLA"));
        
        boolean delivered = game.deliverIceCream(mockCustomer);
        assertTrue(delivered, "La consegna dovrebbe riuscire se il cliente riceve il gelato");

        verify(mockCustomer).receiveIceCream(any());
    }

    @Test
    void testDeliverIceCreamWithNullCustomer() {
        game.start(1);
        boolean delivered = game.deliverIceCream(null);
        assertFalse(delivered, "La consegna non dovrebbe riuscire se il cliente è null");
    }

    @Test
    void testCancelIceCreamResetsBuilder() {
        when(mockCustomer.receiveIceCream(any())).thenReturn(true);

        game.start(1);
        game.chooseCone(Conetype.CLASSIC);
        game.addIngredient(IngredientFactory.createIngredient("VANILLA"));
        game.cancelIceCream();

        game.chooseCone(Conetype.CLASSIC);
        game.addIngredient(IngredientFactory.createIngredient("CHOCOLATE"));

        boolean delivered = game.deliverIceCream(mockCustomer);
        assertTrue(delivered, "La consegna successiva dovrebbe funzionare con un nuovo gelato");
        verify(mockCustomer).receiveIceCream(any());
    }
}
