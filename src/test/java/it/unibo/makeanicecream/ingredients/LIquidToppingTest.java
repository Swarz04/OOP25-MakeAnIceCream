package it.unibo.makeanicecream.ingredients;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import it.unibo.makeanicecream.model.ingredient.IngredientType;
import it.unibo.makeanicecream.model.ingredient.LiquidTopping;
import it.unibo.makeanicecream.api.LiquidToppingType;


class LIquidToppingTest {

    @Test
    void testConstructorValidName() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        assertNotNull(liquidTopping);
        assertEquals(LiquidToppingType.CHOCOLATE_SYRUP, liquidTopping.getTopping());
        assertEquals(IngredientType.LIQUID_TOPPING, liquidTopping.getType());
    }

    @Test
    void testConstructorNullTopping() {
        assertThrows(NullPointerException.class, () -> new LiquidTopping(null));
    }

    @Test
    void testGetTopping() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.STRAWBERRY_SYRUP);
        assertEquals(LiquidToppingType.STRAWBERRY_SYRUP, liquidTopping.getTopping());
    }

    @Test
    void testGetType() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        assertEquals(IngredientType.LIQUID_TOPPING, liquidTopping.getType());
    }

    @Test
    void testEqualsSameTopping() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        assertEquals(liquidTopping, liquidTopping);
    }

    @Test
    void testEqualsDifferentTopping() {
        final LiquidTopping liquidTopping1 = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        final LiquidTopping liquidTopping2 = new LiquidTopping(LiquidToppingType.STRAWBERRY_SYRUP);
        assertNotNull(liquidTopping1);
        assertNotNull(liquidTopping2);
        assertNotEquals(liquidTopping1, liquidTopping2);
    }

    @Test
    void testEqualsNull() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        assertNotNull(liquidTopping);
        assertNotEquals(liquidTopping, null);
    }

    @Test
    void testEqualsDifferentClass() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        final Object obj = new Object();
        assertNotNull(liquidTopping);
        assertNotNull(obj);
        assertNotEquals(liquidTopping, obj);
    }

    @Test
    void testHashCode() {
        final LiquidTopping liquidTopping1 = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        final LiquidTopping liquidTopping2 = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        assertEquals(liquidTopping1.hashCode(), liquidTopping2.hashCode());
    }

    @Test
    void testHashCodeDifferentTopping() {
        final LiquidTopping liquidTopping1 = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        final LiquidTopping liquidTopping2 = new LiquidTopping(LiquidToppingType.STRAWBERRY_SYRUP);
        assertNotEquals(liquidTopping1.hashCode(), liquidTopping2.hashCode());
    }

    @Test
    void testHashCodeNullTopping() {
        final LiquidTopping liquidTopping = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        assertNotNull(liquidTopping);
        assertNotEquals(0, liquidTopping.hashCode());
    }
}
