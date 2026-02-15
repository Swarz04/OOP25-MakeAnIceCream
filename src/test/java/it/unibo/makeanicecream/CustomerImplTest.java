package it.unibo.makeanicecream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.function.Consumer;

import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.api.Timer;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.model.customermodel.CustomerImpl;

/**
 * Test for CustomerImpl class.
 * Verifies correct customer behavior.
 * which includes constructor , getters, ice cream reception and callbacks.
 */
class CustomerImplTest {
    private static final String CUSTOMER_NAME = "TestCustomer";
    private static final int DIFFICULTY = 3;
    private static final int MAX_DIFFICULTY = 6;
    private CustomerImpl customer;
    private Order mockOrder;
    private Timer mockTimer;

    @BeforeEach
    void setUp() {
        mockOrder = mock(Order.class);
        mockTimer = mock(Timer.class);
        when(mockTimer.isExpired()).thenReturn(false);

        customer = new CustomerImpl(CUSTOMER_NAME, mockOrder, mockTimer, DIFFICULTY);
    }

    /**
     * Tests that the constructor throws exceptions for invalid parameters.
     */
    @Test
    void testConstructorValidation() {
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerImpl(null, mockOrder, mockTimer, DIFFICULTY));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerImpl("", mockOrder, mockTimer, DIFFICULTY));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerImpl(CUSTOMER_NAME, null, mockTimer, DIFFICULTY));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerImpl(CUSTOMER_NAME, mockOrder, null, DIFFICULTY));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerImpl(CUSTOMER_NAME, mockOrder, mockTimer, 0));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerImpl(CUSTOMER_NAME, mockOrder, mockTimer, MAX_DIFFICULTY));
    }

    /**
     * Tests that the timer is started or paused correctly upon customer creation.
     */
    @Test
    void testTimerStartOnCreation() {
        verify(mockTimer).start();

        when(mockTimer.isExpired()).thenReturn(true);
        new CustomerImpl(CUSTOMER_NAME, mockOrder, mockTimer, DIFFICULTY);
        verify(mockTimer).pause();
    }

    /**
     * Tests that getter methods return the correct values.
     */
    @Test
    void testGetters() {
        assertEquals(CUSTOMER_NAME, customer.getName());
        assertEquals(mockOrder, customer.getOrder());
        assertEquals(mockTimer, customer.getTimer());
        assertEquals(DIFFICULTY, customer.getDifficulty());
    }

    /**
     * Tests that receiving an ice cream delegates to the order for satisfaction check.
     */
    @Test
    void testReceiveIceCream() {
        final Icecream mockIceCream = mock(Icecream.class);
        when(mockOrder.isSatisfiedBy(mockIceCream)).thenReturn(true);

        assertTrue(customer.receiveIceCream(mockIceCream));

        when(mockOrder.isSatisfiedBy(mockIceCream)).thenReturn(false);
        assertFalse(customer.receiveIceCream(mockIceCream));
    }

    /**
     * Tests that the callback is correctly invoked when receiving an ice cream.
     */
    @Test
    void testOrderResultCallback() {
        @SuppressWarnings("unchecked")
        final Consumer<Boolean> mockCallback = mock(Consumer.class);
        customer.setOrderResultCallback(mockCallback);

        final Icecream mockIceCream = mock(Icecream.class);
        when(mockOrder.isSatisfiedBy(mockIceCream)).thenReturn(true);

        customer.receiveIceCream(mockIceCream);
        verify(mockCallback).accept(true);
    }

    /**
     * Tests that an exception is thrown if a null ice cream is passed.
     */
    @Test
    void testNullIceCream() {
        assertThrows(NullPointerException.class,
        () -> customer.receiveIceCream(null));
    }
}
