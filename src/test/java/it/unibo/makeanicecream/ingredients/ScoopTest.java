package it.unibo.makeanicecream.ingredients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import it.unibo.makeanicecream.model.ingredient.IngredientType;
import it.unibo.makeanicecream.model.ingredient.Scoop;
import it.unibo.makeanicecream.api.FlavorType;

class ScoopTest {

    @Test
    void testConstructorValidName() {
        final Scoop scoop = new Scoop(FlavorType.VANILLA);
        assertNotNull(scoop);
        assertEquals(FlavorType.VANILLA, scoop.getFlavor());
        assertEquals(IngredientType.SCOOP, scoop.getType());
    }

    @Test
    void testConstructorNullFlavor() {
        assertThrows(NullPointerException.class, () -> new Scoop(null));
    }

    @Test
    void testGetFlavor() {
        final Scoop scoop = new Scoop(FlavorType.CHOCOLATE);
        assertEquals(FlavorType.CHOCOLATE, scoop.getFlavor());
    }

    @Test
    void testGetType() {
        final Scoop scoop = new Scoop(FlavorType.STRAWBERRY);
        assertEquals(IngredientType.SCOOP, scoop.getType());
    }

    @Test
    void testEqualsSameFlavor() {
        final Scoop scoop = new Scoop(FlavorType.GREENAPPLE);
        assertEquals(scoop, scoop);
    }

    @Test
    void testEqualsDifferentFlavor() {
        final Scoop scoop1 = new Scoop(FlavorType.GREENAPPLE);
        final Scoop scoop2 = new Scoop(FlavorType.VANILLA);
        assertNotNull(scoop1);
        assertNotNull(scoop2);
        assertNotEquals(scoop1, scoop2);
    }

    @Test
    void testEqualsNull() {
        final Scoop scoop = new Scoop(FlavorType.CHOCOLATE);
        assertNotNull(scoop);
        assertNotEquals(scoop, null);
    }

    @Test
    void testEqualsDifferentClass() {
        final Scoop scoop = new Scoop(FlavorType.STRAWBERRY);
        final Object obj = new Object();
        assertNotNull(scoop);
        assertNotNull(obj);
        assertNotEquals(scoop, obj);
    }

    @Test
    void testHashCodeSameFlavor() {
        final Scoop scoop1 = new Scoop(FlavorType.VANILLA);
        final Scoop scoop2 = new Scoop(FlavorType.VANILLA);
        assertEquals(scoop1.hashCode(), scoop2.hashCode());
    }

    @Test
    void testHashCodeDifferentFlavor() {
        final Scoop scoop1 = new Scoop(FlavorType.VANILLA);
        final Scoop scoop2 = new Scoop(FlavorType.CHOCOLATE);
        assertNotEquals(scoop1.hashCode(), scoop2.hashCode());
    }

    @Test
    void testHashCodeConsistency() {
        final Scoop scoop = new Scoop(FlavorType.GREENAPPLE);
        final int initialHashCode = scoop.hashCode();
        assertEquals(initialHashCode, scoop.hashCode());
        assertEquals(initialHashCode, scoop.hashCode());
    }
}
