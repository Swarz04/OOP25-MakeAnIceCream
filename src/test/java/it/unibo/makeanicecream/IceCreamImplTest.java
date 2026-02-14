package it.unibo.makeanicecream;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.FlavorType;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.LiquidToppingType;
import it.unibo.makeanicecream.model.IceCreamImpl;
import it.unibo.makeanicecream.model.ingredient.LiquidTopping;
import it.unibo.makeanicecream.model.ingredient.Scoop;

class IceCreamImplTest {
    private Conetype cone;
    private List<Ingredient> ingredients;
    private IceCreamImpl iceCream;

    @BeforeEach
    void setUp() {
        cone = Conetype.CLASSIC;
        ingredients = new ArrayList<>();
        ingredients.add(new Scoop(FlavorType.VANILLA));
        ingredients.add(new LiquidTopping(LiquidToppingType.STRAWBERRY_SYRUP));
        iceCream = new IceCreamImpl(cone, ingredients, false);
    }

    @Test
    void testConstructorValidParameters() {
        assertNotNull(iceCream);
        assertEquals(cone, iceCream.getConetype());
        assertEquals(ingredients, iceCream.getIngredients());
        assertFalse(iceCream.isClosed());
    }

    @Test
    void testConstructorNullCone() {
        assertThrows(NullPointerException.class, () -> new IceCreamImpl(null, ingredients, false));
    }

    @Test
    void testConstructorNullIngredients() {
        assertThrows(NullPointerException.class, () -> new IceCreamImpl(cone, null, false));
    }

    @Test
    void testConstructorWithNullIngredientList(){
        ingredients.add(null);
        assertThrows(NullPointerException.class, () -> new IceCreamImpl(cone, ingredients, false));
    }

    @Test
    void testGetConetype() {
        assertEquals(cone, iceCream.getConetype());
    }

    @Test
    void testGetIngredients() {
        assertEquals(ingredients, iceCream.getIngredients());
    }

    @Test
    void testIsClosed() {
        assertFalse(iceCream.isClosed());
        final IceCreamImpl closedIceCream = new IceCreamImpl(cone, ingredients, true);
        assertTrue(closedIceCream.isClosed());
    }

    @Test
    void testGetIngredientsUnmodifiable() {
        final List<Ingredient> returned = iceCream.getIngredients();
        assertThrows(UnsupportedOperationException.class, () -> returned.clear());
        assertThrows(UnsupportedOperationException.class, () -> returned.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> returned.add(new Scoop(FlavorType.CHOCOLATE)));
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(iceCream, iceCream);
    }

    @Test
    void testEqualsNull() {
        assertNotNull(iceCream);
        assertFalse(iceCream.equals(null)); 
    }

    @Test
    void testEmptyIngredients() {
        final IceCreamImpl emptyIngredientsIceCream = new IceCreamImpl(cone, new ArrayList<>(), false);
        assertNotNull(emptyIngredientsIceCream);
        assertTrue(emptyIngredientsIceCream.getIngredients().isEmpty());
    }

    @Test
    void testToString() {
        final String result = iceCream.toString();
        assertNotNull(result);
        assertTrue(result.contains("CLASSIC"));
        assertTrue(result.contains("VANILLA"));
        assertTrue(result.contains("STRAWBERRY_SYRUP"));
    }

    @Test
    void testToStringWithEmptyIngredients() {
        final IceCreamImpl emptyIceCream = new IceCreamImpl(cone, new ArrayList<>(), false);
        final String result = emptyIceCream.toString();
        assertNotNull(result);
        assertTrue(result.contains("(empty)"));
    }
}
