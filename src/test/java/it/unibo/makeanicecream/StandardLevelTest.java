package it.unibo.makeanicecream.model.level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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

class StandardLevelTest {

    private static final int INITIAL_LIVES = 3;
    private static final int DIFFICULTY = 1;
    private static final double DELTA_TIME = 0.1;
    private static final int ZERO_LIVES = 0;

    private Level level;
    private Customer customer1Mock;
    private Customer customer2Mock;
    private Timer timer1Mock;
    @SuppressWarnings("unused")
    private Timer timer2Mock;

    @BeforeEach
    void setUp() {
        customer1Mock = mock(Customer.class);
        customer2Mock = mock(Customer.class);
        timer1Mock = mock(Timer.class);
        timer2Mock = mock(Timer.class);

        when(customer1Mock.getTimer()).thenReturn(timer1Mock);
        when(customer2Mock.getTimer()).thenReturn(timer2Mock);

        final Queue<Customer> customers = new ArrayDeque<>(List.of(customer1Mock, customer2Mock));
        level = new StandardLevel(DIFFICULTY, INITIAL_LIVES, customers);
    }

    @Test
    void testInitialState() {

    }

    @Test
    void testLoseLife() {

    }

    @Test
    void testServeCustomerSuccessfully() {

    }

    @Test
    void testNotifyCustomerServedSuccess() {

    }

    @Test
    void testNotifyCustomerServedFailure() {
    }

    @Test
    void testUpdateTimerAndExpiration() {

    }

    @Test
    void testUpdateWithNoCustomers() {
        level.serveCurrentCustomer();
        level.serveCurrentCustomer();

        // Should not throw exception
        level.update(DELTA_TIME);
    }
}
