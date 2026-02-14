package it.unibo.makeanicecream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Level;
import it.unibo.makeanicecream.api.Timer;
import it.unibo.makeanicecream.model.level.StandardLevel;

class StandardLevelTest {

    private static final int INITIAL_LIVES = 3;
    private static final int DIFFICULTY = 1;
    private static final double DELTA_TIME = 0.1;

    private Level level;
    private Customer customer1Mock;
    private Timer timer1Mock;

    @BeforeEach
    void setUp() {
        customer1Mock = mock(Customer.class);
        final Customer customer2Mock = mock(Customer.class);
        timer1Mock = mock(Timer.class);
        final Timer timer2Mock = mock(Timer.class);

        when(customer1Mock.getTimer()).thenReturn(timer1Mock);
        when(customer2Mock.getTimer()).thenReturn(timer2Mock);

        final Queue<Customer> customers = new ArrayDeque<>(List.of(customer1Mock, customer2Mock));
        level = new StandardLevel(DIFFICULTY, INITIAL_LIVES, customers);
    }

    @Test
    void testInitialState() {
        assertEquals(INITIAL_LIVES, level.getLives());
        assertEquals(DIFFICULTY, level.getDifficulty());
        assertTrue(level.hasNextCustomer());
    }

    @Test
    void testLoseLife() {
        level.loseLife();
        assertEquals(INITIAL_LIVES - 1, level.getLives());
    }

    @Test
    void testServeCustomerSuccessfully() {
        level.serveCurrentCustomer();
        assertTrue(level.hasNextCustomer()); // Ne resta ancora uno
        level.serveCurrentCustomer();
        assertFalse(level.hasNextCustomer()); // Finiti!
    }

    @Test
    void testNotifyCustomerServedSuccess() {
        level.notifyCustomerServed(true);
        assertEquals(INITIAL_LIVES, level.getLives()); // Vita intatta
        assertNotSame(customer1Mock, level.getCurrentCustomer()); // Cliente cambiato
    }

    @Test
    void testNotifyCustomerServedFailure() {
        level.notifyCustomerServed(false);
        assertEquals(INITIAL_LIVES - 1, level.getLives()); // Una vita in meno
    }

    @Test
    void testUpdateTimerAndExpiration() {
        when(timer1Mock.isExpired()).thenReturn(true);
        level.update(DELTA_TIME);
        assertEquals(INITIAL_LIVES - 1, level.getLives()); // Tempo scaduto = vita persa
    }

    @Test
    void testUpdateWithNoCustomers() {
        level.serveCurrentCustomer();
        level.serveCurrentCustomer();

        // Should not throw exception
        level.update(DELTA_TIME);
    }

    @Test
    void testCallbacksRegistered() {
        verify(customer1Mock).setOrderResultCallback(any());
    }
}
