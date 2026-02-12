package it.unibo.makeanicecream.engine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the {@link GameLoopImpl} class.
 */
class GameLoopImplTest {
    private static final long PERIOD = 16;
    private static final long MIN_ELAPSED_MS = 0;
    private static final long MAX_ELAPSED_MS = 100;
    private static final long LOOP_RUN_MS = 50;

    private Consumer<Long> updater;
    private GameLoopImpl loop;

    /**
     * Sets up a new GameLoopImpl instance with a mocked updater
     * before each test.
     */
    @BeforeEach
    @SuppressWarnings("unchecked") // Safe cast: mocking raw Consumer as Consumer<Long> for testing
    void setUp() {
        updater = (Consumer<Long>) mock(Consumer.class);
        loop = new GameLoopImpl(PERIOD, updater);
    }

    /**
     * Test that start() sets running to true.
     */
    @Test
    void startShouldSetRunningTrue() {
        loop.start();
        assertTrue(loop.isRunning());
        loop.stop();
    }

    /**
     * Test that stop() sets running to false.
     */
    @Test
    void stopShouldSetRunningFalse() {
        loop.start();
        loop.stop();
        assertFalse(loop.isRunning());
    }


    /**
     * Tests that GameLoopImpl begins the loop
     * and calls the updater at least once.
     */
    @Test
    void shouldCallUpdaterWhenStarted() throws InterruptedException {
        runLoopForTest();
        verify(updater, atLeastOnce()).accept(anyLong());
    }

    /**
     * Tests that GameLoopImpl correctly stops the loop.
     */
    @Test
    void shouldStopLoopWhenStopped() throws InterruptedException {
        runLoopForTest();
        assertFalse(loop.isRunning(), "Loop should not be running after stop()");
    }

    /**
     * Tests that the updater receives a non-negative elapsed time.
     */
    @Test
    void updaterShouldReceiveNonNegativeElapsedTime() throws InterruptedException {
        runLoopForTest();
        verify(updater, atLeastOnce()).accept(argThat(elapsed -> elapsed >= MIN_ELAPSED_MS));
    }

    /**
     * Tests that the updater receives an elapsed time capped at 100 ms.
     */
    @Test
    void updaterShouldReceiveElapsedTimeNoGreaterThan100() throws InterruptedException {
        runLoopForTest();
        verify(updater, atLeastOnce()).accept(argThat(elapsed -> elapsed <= MAX_ELAPSED_MS));
    }

    /**
     * Starts the loop, lets it run for a short duration, and then stops it.
     *
     * @throws InterruptedException if the sleep is interrupted
     */
    private void runLoopForTest() throws InterruptedException {
        loop.start();
        Thread.sleep(LOOP_RUN_MS);
        loop.stop();
    }
}
