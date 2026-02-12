package it.unibo.makeanicecream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Player;
import it.unibo.makeanicecream.model.IngredientFactory;
import it.unibo.makeanicecream.model.PlayerImpl;

class PlayerTest {

    private static final String VANILLA = "VANILLA";
    private Player player;
    private Customer customerMock;

    @BeforeEach
    void setUp() {
        player = new PlayerImpl();
        customerMock = mock(Customer.class);
    }

    @Test
    void testChooseCone() {
        assertTrue(player.chooseCone(Conetype.CLASSIC));
        assertFalse(player.chooseCone(null));
    }

    @Test
    void testAddIngredient() {
        player.chooseCone(Conetype.FLOWER);
        final Ingredient scoop = IngredientFactory.createIngredient(VANILLA);
        assertTrue(player.addIngredient(scoop));
    }

    @Test
    void testAddNullIngredient() {
        player.chooseCone(Conetype.CLASSIC);
        assertFalse(player.addIngredient(null));
    }

    @Test
    void testDeliverIceCreamSuccess() {
        player.chooseCone(Conetype.CLASSIC);
        player.addIngredient(IngredientFactory.createIngredient("CHOCOLATE"));

        when(customerMock.receiveIceCream(any())).thenReturn(true);

        assertTrue(player.deliverIceCream(customerMock));
        verify(customerMock).receiveIceCream(any());

        // Verify reset: adding ingredient should fail as cone is cleared
        assertFalse(player.addIngredient(IngredientFactory.createIngredient(VANILLA)));
    }

    @Test
    void testDeliverIceCreamFailure() {
        player.chooseCone(Conetype.CLASSIC);
        player.addIngredient(IngredientFactory.createIngredient("STRAWBERRY"));

        when(customerMock.receiveIceCream(any())).thenReturn(false);

        assertFalse(player.deliverIceCream(customerMock));

        // Verify reset: adding ingredient should fail as cone is cleared
        assertFalse(player.addIngredient(IngredientFactory.createIngredient(VANILLA)));
    }

    @Test
    void testDeliverNullCustomer() {
        assertFalse(player.deliverIceCream(null));
    }

    @Test
    void testDeliverIncompleteIceCream() {
        assertThrows(IllegalStateException.class, () -> player.deliverIceCream(customerMock));
    }

    @Test
    void testCancelIceCream() {
        player.chooseCone(Conetype.CLASSIC);
        player.cancelIceCream();
        // After cancel, adding an ingredient should fail because the cone selection is reset
        assertFalse(player.addIngredient(IngredientFactory.createIngredient(VANILLA)));
    }
}
