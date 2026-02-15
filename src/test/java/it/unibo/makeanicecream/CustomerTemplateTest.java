package it.unibo.makeanicecream;

import it.unibo.makeanicecream.model.customermodel.CustomerTemplate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

/**
 * Test for the CustomerTemplate class.
 * Verifies the correct tempalte creation and name cycling behavior
 */
class CustomerTemplateTest {

    private static final String TEST1 = "Test1";
    private static final String TEST2 = "Test2";
    private static final String MARIO = "Mario";
    private static final String MARIA = "Maria";
    private static final String PAOLO = "Paolo";

    /**
     * Verifies that the constructor throws exceptions
     * for invalid parameters.
     */
    @Test
    void testConstructorValidation() {
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(null, 2, 1));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(new String[]{}, 2, 1));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(new String[]{TEST1}, 0, 1));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(new String[]{TEST1}, -1, 1));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(new String[]{TEST1}, 2, -1));
    }

    /**
     * Verifies that a template with a single name.
     * always returns the same name.
     */
    @Test
    void testSingleNameTemplate() {
        final String[] names = {MARIO};
        final CustomerTemplate template = new CustomerTemplate(names, 2, 1);

        assertEquals(MARIO, template.getNextName());
        assertEquals(MARIO, template.getNextName());
        assertEquals(2, template.getScoopCount());
        assertEquals(1, template.getToppingCount());
    }

    /**
     * Verifies alternating behavior between two names.
     */
    @Test
    void testTwoNamesTemplate() {
        final String[] names = {MARIA, PAOLO};
        final CustomerTemplate template = new CustomerTemplate(names, 1, 0);

        assertEquals(MARIA, template.getNextName());
        assertEquals(PAOLO, template.getNextName());
        assertEquals(MARIA, template.getNextName());
        assertEquals(PAOLO, template.getNextName());
    }

    /**
     * Verifies that getPossiblesNames return a copy.
     * Instead of the original.
     */
    @Test
    void testGetPossiblesNames() {
        final String[] original = {TEST1, TEST2};
        final CustomerTemplate template = new CustomerTemplate(original, 2, 1);

        final String[] copy = template.getPossibleNames();
        assertNotSame(original, copy);
        assertArrayEquals(original, copy);

        copy[0] = "Modified";
        assertNotEquals(copy[0], template.getPossibleNames()[0]);
    }

    /**
     * Test the string representation of the template.
     */
    @Test
    void testToString() {
        final CustomerTemplate template = new CustomerTemplate(new String[]{TEST1, TEST2}, 3, 2); 
        final String toString = template.toString();
        assertTrue(toString.contains("scoops=3"));
        assertTrue(toString.contains("toppings=2"));
        assertTrue(toString.contains(TEST1));
        assertTrue(toString.contains(TEST2));
    }
}
