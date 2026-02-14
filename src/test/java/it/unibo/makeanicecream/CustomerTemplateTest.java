package it.unibo.makeanicecream;


import it.unibo.makeanicecream.model.customermodel.CustomerTemplate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test for the CustomerTemplate class.
 * Verifies the correct tempalte creation and name cycling behavior
 */
public class CustomerTemplateTest {
    
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
            () -> new CustomerTemplate(new String[]{"Test"}, 0,1));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(new String[]{"Test"}, -1, 1));
        assertThrows(IllegalArgumentException.class,
            () -> new CustomerTemplate(new String[]{"Test"}, 2, -1));
    }
    
    /**
     * Verifies that a template with a single name.
     * always returns the same name.
     */
    @Test
    void testSingleNameTemplate() {
        String[] names = {"Mario"};
        CustomerTemplate template = new CustomerTemplate(names, 2, 1);

        assertEquals("Mario", template.getNextName());
        assertEquals("Mario", template.getNextName());
        assertEquals(2, template.getScoopCount());
        assertEquals(1, template.getToppingCount());
    }
    
    /**
     * Verifies alternating behavior between two names.
     */
    @Test
    void testTwoNamesTemplate(){
        String[] names = {"Maria", "Paolo"};
        CustomerTemplate template = new CustomerTemplate(names, 1 ,0);

        assertEquals("Maria", template.getNextName());
        assertEquals("Paolo", template.getNextName());
        assertEquals("Maria", template.getNextName());
        assertEquals("Paolo", template.getNextName());
    }
    
    /**
     * Verifies that getPossiblesNames return a copy.
     * Instead of the original.
     */
    @Test
    void testGetPossiblesNames() {
        String[] original = {"Test1", "Test2"};
        CustomerTemplate template = new CustomerTemplate(original, 2, 1);

        String[] copy = template.getPossibleNames();
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
        CustomerTemplate template = new CustomerTemplate(
            new String[]{"Test1", "Test2"}, 3, 2);
        
        String toString = template.toString();
        assertTrue(toString.contains("scoops=3"));
        assertTrue(toString.contains("toppings=2"));
        assertTrue(toString.contains("Test1"));
        assertTrue(toString.contains("Test2"));
    }
}
