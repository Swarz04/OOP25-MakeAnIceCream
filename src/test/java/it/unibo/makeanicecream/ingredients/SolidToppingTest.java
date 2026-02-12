package it.unibo.makeanicecream.ingredients;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import it.unibo.makeanicecream.model.ingredient.IngredientType;
import it.unibo.makeanicecream.model.ingredient.SolidTopping;
import it.unibo.makeanicecream.api.SolidToppingType;

class SolidToppingTest {
    @Test
    void testConstructorValidName() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.COOKIES);
        assertNotNull(solidTopping);
        assertEquals(SolidToppingType.COOKIES, solidTopping.getTopping());
        assertEquals(IngredientType.SOLID_TOPPING, solidTopping.getType());
    }

    @Test
    void testConstructorNullTopping() {
        assertThrows(NullPointerException.class, () -> new SolidTopping(null));
    }

    @Test
    void testGetTopping() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.CHERRY);
        assertEquals(SolidToppingType.CHERRY, solidTopping.getTopping());
    }

    @Test
    void testGetType() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.CHERRY);
        assertEquals(IngredientType.SOLID_TOPPING, solidTopping.getType());
    }

    @Test
    void testEqualsSameTopping() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.CHERRY);
        assertEquals(solidTopping, solidTopping);
    }

    @Test
    void testEqualsDifferentTopping() {
        final SolidTopping solidTopping1 = new SolidTopping(SolidToppingType.CHERRY);
        final SolidTopping solidTopping2 = new SolidTopping(SolidToppingType.COOKIES);
        assertNotNull(solidTopping1);
        assertNotNull(solidTopping2);
        assertNotEquals(solidTopping1, solidTopping2);
    }

    @Test
    void testEqualsNull() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.CHERRY);
        assertNotNull(solidTopping);
        assertNotEquals(solidTopping, null);
    }

    @Test
    void testHashCodeSameTopping() {
        final SolidTopping solidTopping1 = new SolidTopping(SolidToppingType.CHERRY);
        final SolidTopping solidTopping2 = new SolidTopping(SolidToppingType.CHERRY);
        assertEquals(solidTopping1.hashCode(), solidTopping2.hashCode());
    }

    @Test
    void testHashCodeDifferentTopping() {
        final SolidTopping solidTopping1 = new SolidTopping(SolidToppingType.CHERRY);
        final SolidTopping solidTopping2 = new SolidTopping(SolidToppingType.COOKIES);
        assertNotEquals(solidTopping1.hashCode(), solidTopping2.hashCode());
    }

    @Test
    void testEqualsDifferentClass() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.CHERRY);
        final Object obj = new Object();
        assertNotNull(solidTopping);
        assertNotNull(obj);
        assertNotEquals(solidTopping, obj);
    }

    @Test
    void testHashCodeNullTopping() {
        final SolidTopping solidTopping = new SolidTopping(SolidToppingType.CHERRY);
        assertNotNull(solidTopping);
        assertNotEquals(0, solidTopping.hashCode());
    }
}
