package it.unibo.makeanicecream;

import it.unibo.makeanicecream.model.customermodel.CustomerTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test for the CustomerTimer class.
 * Verifies the correct behavior of the timer.
 * Which includes start, pause, udpate and expiration callback.
 */
@ExtendWith(MockitoExtension.class)
class CustomerTimerTest {
    private static final int NEGATIVE_TIME = -10;
    private static final double INITIAL_TIME = 60.0;
    private static final double DELTA = 0.001;
    private static final double TIME_5 = 5.0;
    private static final double TIME_15 = 15.0;
    private static final double TIME_20 = 20.0;
    private static final double TIME_25 = 25.0;
    private static final double TIME_30 = 30.0;
    private static final double TIME_50 = 50.0;

    @Mock
    private Runnable mockCallback;
    private CustomerTimer timer;

    @BeforeEach
    void setUp() {
        timer = new CustomerTimer(INITIAL_TIME);
    }

    /**
     * Test that the constructor throws exceptions for non positive values.
     */
    @Test
    void testConstructorValidation() {
        assertThrows(IllegalArgumentException.class,
        () -> new CustomerTimer(0));
        assertThrows(IllegalArgumentException.class,
        () -> new CustomerTimer(NEGATIVE_TIME));
    }

    /**
     * Tests the initial state of the timer.
     * which includes: paused, not expired and correct TimeLeft
     */
    @Test
    void testInitialState() {
        assertTrue(timer.isPaused());
        assertFalse(timer.isExpired());
        assertEquals(INITIAL_TIME, timer.getTimeLeft(), DELTA);
    }

    /**
     * Test that timer does not update while paused.
     */
    @Test
    void testNoUpdateWhenPaused() {
        timer.update(10.0);
        assertEquals(INITIAL_TIME, timer.getTimeLeft());
        assertFalse(timer.isExpired());
    }

    /**
     * Test that the timer updates correctly once started.
     */
    @Test
    void testUpdateWhenStarted() {
        timer.start();
        timer.update(10.0);
        assertEquals(INITIAL_TIME - 10.0, timer.getTimeLeft(), DELTA);
        assertFalse(timer.isExpired());
    }

    /**
     * Test pause and resume functionality.
     */
    @Test
    void testPauseResume() {
        timer.start();
        timer.update(10.0);

        timer.pause();
        timer.update(10.0);
        assertEquals(INITIAL_TIME - 10.0, timer.getTimeLeft(), DELTA);

        timer.resume();
        timer.update(TIME_5);
        assertEquals(INITIAL_TIME - TIME_15, timer.getTimeLeft(), DELTA);
    }

    /**
     * Tests that the timer expires when the time reaches zero.
     */
    @Test
    void testExpiration() {
        timer.start();
        timer.update(INITIAL_TIME + 10.0);

        assertTrue(timer.isExpired());
        assertEquals(0, timer.getTimeLeft(), DELTA);

        timer.update(10.0);
        assertTrue(timer.isExpired());
        assertEquals(0, timer.getTimeLeft(), DELTA);
    }

    /**
     * Tests that the expiration callback is invoked only once.
     */
    @Test
    void testExpirationCallback() {
        final Runnable callbackMock = mock(Runnable.class);
        timer.setOnExpired(callbackMock);

        timer.start();
        timer.update(INITIAL_TIME - 1);
        verify(callbackMock, never()).run();

        timer.update(2);
        verify(callbackMock, times(1)).run();

        timer.update(1);
        verify(callbackMock, times(1)).run();
    }

    /**
     * Tests that multiple updates are handled correctly.
     */
    @Test
    void testMultipleUpdates() {
        timer.start();

        timer.update(10.0);
        assertEquals(TIME_50, timer.getTimeLeft(), DELTA);

        timer.update(TIME_20);
        assertEquals(TIME_30, timer.getTimeLeft(), DELTA);

        timer.update(TIME_25);
        assertEquals(TIME_5, timer.getTimeLeft(), DELTA);

        timer.update(10.0);
        assertTrue(timer.isExpired());
        assertEquals(0, timer.getTimeLeft(), DELTA);
    }
}
