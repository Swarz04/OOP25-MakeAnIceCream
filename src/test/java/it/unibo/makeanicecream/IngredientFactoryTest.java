package it.unibo.makeanicecream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.makeanicecream.model.IngredientFactory;
import it.unibo.makeanicecream.model.ingredient.IngredientType;
import it.unibo.makeanicecream.api.Ingredient;

class IngredientFactoryTest {

    @Test
    void testCreateScoop() {
        final Ingredient scoop = IngredientFactory.createIngredient("VANILLA");
        assertNotNull(scoop);
        assertEquals(IngredientType.SCOOP, scoop.getType());
    }

    @Test
    void testCreateLiquidTopping() {
        final Ingredient liquidTopping = IngredientFactory.createIngredient("CHOCOLATE_SYRUP");
        assertNotNull(liquidTopping);
        assertEquals(IngredientType.LIQUID_TOPPING, liquidTopping.getType());
    }

    @Test
    void testCreateSolidTopping() {
        final Ingredient solidTopping = IngredientFactory.createIngredient("COOKIES");
        assertNotNull(solidTopping);
        assertEquals(IngredientType.SOLID_TOPPING, solidTopping.getType());
    }

    @Test
    void testCreateUnknownIngredient() {
        assertThrows(IllegalArgumentException.class, () -> IngredientFactory.createIngredient("UNKNOWN"));
    }

    @Test
    void testCreateIngredientNullName() {
        assertThrows(IllegalArgumentException.class, () -> IngredientFactory.createIngredient(null));
    }
}
