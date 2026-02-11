package it.unibo.makeanicecream.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.Level;
import it.unibo.makeanicecream.api.Customer;

/**
 * Unit test for {@link DeliverCommand}.
 * 
 * <p>
 * This test verifies that executing the DeliverCommand correctly
 * delivers the current ice cream to the active customer in the game.
 * </p>
 */
class DeliverCommandTest {

    @Test
    void shouldDeliverIceCreamToCurrentCustomer() {
        final Game game = mock(Game.class);
        final Level level = mock(Level.class);
        final Customer customer = mock(Customer.class);
        final DeliverCommand command = new DeliverCommand();

        when(game.getCurrentLevel()).thenReturn(level);
        when(level.getCurrentCustomer()).thenReturn(customer);

        command.execute(game);
        verify(game).deliverIceCream(customer);
    }
}
