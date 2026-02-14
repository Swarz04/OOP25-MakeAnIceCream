package it.unibo.makeanicecream;
import it.unibo.makeanicecream.model.customermodel.CustomerTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test for the CustomerTimer class.
 * Verifies the correct behavior of the timer.
 * Which includes start, pause, udpate and expiration callback.
 */
class CustomerTimerTest {

    private CustomerTimer timer;
    private static final double INITIAL_TIME = 60.0;

    @BeforeEach
    void setUp() {
        timer = new CustomerTimer(INITIAL_TIME);
    }
    
    /**
     * Test that the constructor throws exceptions for non positive values.
     */
    @Test
    void testConstructorValidation(){
        assertThrows(IllegalArgumentException.class,
        () -> new CustomerTimer(0));
        assertThrows(IllegalArgumentException.class,
        () -> new CustomerTimer(-10));
    }

    /**
     * Tests the initial state of the timer.
     * which includes: paused, not expired and correct TimeLeft
     */
    @Test
    void testInitialState() {
        assertTrue(timer.isPaused());
        assertFalse(timer.isExpired());
        assertEquals(INITIAL_TIME, timer.getTimeLeft(), 0.001);
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
        assertEquals(INITIAL_TIME -10.0, timer.getTimeLeft(), 0.001);
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
        assertEquals(INITIAL_TIME - 10.0, timer.getTimeLeft(), 0.001);

        timer.resume();
        timer.update(10.0);
        assertEquals(INITIAL_TIME - 15.0, timer.getTimeLeft(), 0.001);
    }

    /**
     * Tests that the timer expires when the time reaches zero.
     */
    @Test
    void testExpiration() {
        timer.start();
        timer.update(INITIAL_TIME + 10.0);

        assertTrue(timer.isExpired());
        assertEquals(0, timer.getTimeLeft(), 0.001);

        timer.update(10.0);
        assertTrue(timer.isExpired());
        assertEquals(0, timer.getTimeLeft(), 0.001);
    }

    /**
     * Tests that the expiration callback is invoked only once.
     */
    @Test
    void testExpirationCallback() {
        Runnable mockCallback = mock(Runnable.class);
        timer.setOnExpired(mockCallback);

        timer.start();
        timer.update(INITIAL_TIME - 1);
        verify(mockCallback, never()).run();

        timer.update(2);
        verify(mockCallback, times(1)).run();

        timer.update(1);
        verify(mockCallback, times(1)).run();
    }

    /**
     * Tests that multiple updates are handled correctly.
     */
    @Test
    void testMultipleUpdates() {
        timer.start();

        timer.update(10.0);
        assertEquals(50.0, timer.getTimeLeft(), 0.001);

        timer.update(20.0);
        assertEquals(30.0, timer.getTimeLeft(), 0.001);

        timer.update(25.00);
        assertEquals(5.0, timer.getTimeLeft(), 0.001);

        timer.update(10.0);
        assertTrue(timer.isExpired());
        assertEquals(0, timer.getTimeLeft(), 0.001);
    }
}