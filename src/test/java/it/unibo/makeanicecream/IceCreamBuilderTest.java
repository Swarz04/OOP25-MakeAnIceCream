package it.unibo.makeanicecream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.model.IngredientFactory;
import it.unibo.makeanicecream.model.IceCreamBuilder;
import it.unibo.makeanicecream.model.IceCreamImpl;
import it.unibo.makeanicecream.model.ingredient.IngredientType;

/**
 * Test class for IceCreamBuilder.
 */
class IceCreamBuilderTest {
    private static final String CHOCOLATE_SYRUP = "CHOCOLATE_SYRUP";
    private static final String VANILLA = "VANILLA";
    private static final String GREENAPPLE = "GREENAPPLE";
    private static final String COOKIES = "COOKIES";
    private static final String STRAWBERRY = "STRAWBERRY";
    private static final String CHOCOLATE = "CHOCOLATE";

    private IceCreamBuilder builder;

    /**
     * Set up a new IceCreamBuilder before each test.
     */
    @BeforeEach
    void setUp() {
        builder = new IceCreamBuilder();
    }

    /**
     * Test the normal flow of building an ice cream with a cone, scoops, and toppings, and then submitting it.
     */
    @Test
    void testBuildIceCream() {
        // Select a cone type
        assertTrue(builder.chooseCone(Conetype.CLASSIC));
        // Add a scoop
        final Ingredient scoop = IngredientFactory.createIngredient(STRAWBERRY);
        assertTrue(builder.addIngredient(scoop));
        // Enable toppings and add a liquid topping
        builder.setToppingEnabled(true);
        final Ingredient liquidTopping = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        assertTrue(builder.addIngredient(liquidTopping));
        // Add a solid topping
        final Ingredient solidTopping = IngredientFactory.createIngredient(COOKIES);
        assertTrue(builder.addIngredient(solidTopping));
        // Try to submit the ice cream
        final IceCreamImpl iceCream = builder.submit();
        assertNotNull(iceCream);
    }

    /**
     * Test that trying to choose a null cone returns false and does not set the cone.
     */
    @Test
    void testChooseNullCone() {
        assertFalse(builder.chooseCone(null));
    }

    /**
     * Test that trying to add a null ingredient returns false and does not modify the ice cream.
     */
    @Test
    void testAddNullIngredient() {
        builder.chooseCone(Conetype.CLASSIC);
        assertFalse(builder.addIngredient(null));
    }

    /**
     * Test that trying to add an ingredient without selecting a cone first returns false and does not modify the ice cream.
     */
    @Test
    void testCannotAddIngredientWithoutCone() {
        final Ingredient scoop = IngredientFactory.createIngredient(VANILLA);
        assertFalse(builder.addIngredient(scoop));
    }

    /**
     * Test that the builder enforces a maximum of 3 scoops and does not allow adding more than that.
     */
    @Test
    void testMaxScoops() {
        builder.chooseCone(Conetype.CLASSIC);

        final Ingredient scoop1 = IngredientFactory.createIngredient(CHOCOLATE);
        final Ingredient scoop2 = IngredientFactory.createIngredient(STRAWBERRY);
        final Ingredient scoop3 = IngredientFactory.createIngredient(VANILLA);
        final Ingredient scoop4 = IngredientFactory.createIngredient(GREENAPPLE);

        assertTrue(builder.addIngredient(scoop1));
        assertTrue(builder.addIngredient(scoop2));
        assertTrue(builder.addIngredient(scoop3));
        // Adding a fourth scoop should fail
        assertFalse(builder.addIngredient(scoop4));
    }

    /**
     * Test that cannot add more than 2 liquid toppings per scoop.
     */
    @Test
    void testMaxLiquidToppingPerScoop() {
        builder.chooseCone(Conetype.CLASSIC);
        builder.setToppingEnabled(true);

        final Ingredient scoop = IngredientFactory.createIngredient(CHOCOLATE);
        assertTrue(builder.addIngredient(scoop));

        final Ingredient liquid1 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        final Ingredient liquid2 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        final Ingredient liquid3 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);

        assertTrue(builder.addIngredient(liquid1));
        assertTrue(builder.addIngredient(liquid2));
        assertFalse(builder.addIngredient(liquid3));
    }

    /**
     * Test that can add liquid toppings to different scoops.
     */
    @Test
    void testLiquidToppingOnDifferentScoops() {
        builder.chooseCone(Conetype.CLASSIC);
        builder.setToppingEnabled(true);

        final Ingredient scoop1 = IngredientFactory.createIngredient(CHOCOLATE);
        final Ingredient scoop2 = IngredientFactory.createIngredient(VANILLA);

        assertTrue(builder.addIngredient(scoop1));

        final Ingredient liquid1 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        final Ingredient liquid2 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        assertTrue(builder.addIngredient(liquid1));
        assertTrue(builder.addIngredient(liquid2));

        assertTrue(builder.addIngredient(scoop2));

        final Ingredient liquid3 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        final Ingredient liquid4 = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        assertTrue(builder.addIngredient(liquid3));
        assertTrue(builder.addIngredient(liquid4));
    }

    /**
     * Test that trying to add a topping when toppings are disabled returns false and does not modify the ice cream.
     */
    @Test
    void testCannotAddToppingDisabled() {
        builder.chooseCone(Conetype.CLASSIC);

        final Ingredient scoop = IngredientFactory.createIngredient(CHOCOLATE);
        assertTrue(builder.addIngredient(scoop));

        // Toppings should be disabled by default
        final Ingredient liquidTopping = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        assertFalse(builder.addIngredient(liquidTopping));

        // Enable toppings
        builder.setToppingEnabled(true);
        assertTrue(builder.addIngredient(liquidTopping));
    }

    /**
     * Test that trying to add a topping without any scoops returns false and does not modify the ice cream, 
     * and that after adding a scoop, adding a topping succeeds.
     */
    @Test
    void testNeedScoopBeforeTopping() {
        builder.chooseCone(Conetype.CLASSIC);
        builder.setToppingEnabled(true);

        // Try to add a topping without any scoops
        final Ingredient solidTopping = IngredientFactory.createIngredient(COOKIES);
        assertFalse(builder.addIngredient(solidTopping));

        // Add a scoop and then try again
        final Ingredient scoop = IngredientFactory.createIngredient(GREENAPPLE);
        assertTrue(builder.addIngredient(scoop));
        assertTrue(builder.addIngredient(solidTopping));
    }

    /**
     * Test that after adding a solid topping, the ice cream is closed and no more ingredients can be added.
     */
    @Test
    void testSolidToppingClosesIceCream() {
        builder.chooseCone(Conetype.CLASSIC);
        builder.setToppingEnabled(true);

        final Ingredient scoop = IngredientFactory.createIngredient(VANILLA);
        assertTrue(builder.addIngredient(scoop));

        final Ingredient solidTopping = IngredientFactory.createIngredient(COOKIES);
        assertTrue(builder.addIngredient(solidTopping));

        // After adding a solid topping, no more ingredients can be added
        final Ingredient liquidTopping = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        assertFalse(builder.addIngredient(liquidTopping));
    }

    /**
     * Test that the getIceCream method returns a valid ice cream instance with the correct ingredients after building.
     */
    @Test
    void testGetIceCream() {
        builder.chooseCone(Conetype.CLASSIC);
        final Ingredient scoop = IngredientFactory.createIngredient(GREENAPPLE);
        builder.addIngredient(scoop);

        final IceCreamImpl iceCream = builder.getIceCream();
        assertNotNull(iceCream);
        assertEquals(1, iceCream.getIngredients().size());
        assertEquals(IngredientType.SCOOP, iceCream.getIngredients().get(0).getType());
    }

    /**
     * Test that trying to submit an ice cream without a cone or without scoops throws an IllegalStateException, 
     * and that after fixing those issues, submitting succeeds.
     */
    @Test
    void testSubmitValidation() {
        // Submitting without a cone should throw an exception
        assertThrows(IllegalStateException.class, builder::submit);

        builder.chooseCone(Conetype.CLASSIC);

        // Submitting without any ingredients should throw an exception
        assertThrows(IllegalStateException.class, builder::submit);

        // Submitting without scoops should throw an exception
        final Ingredient liquidTopping = IngredientFactory.createIngredient(CHOCOLATE_SYRUP);
        builder.setToppingEnabled(true);
        builder.addIngredient(liquidTopping);
        assertThrows(IllegalStateException.class, builder::submit);
    }

    /**
     * Test that resetting the builder clears all selections and ingredients, and that after resetting, the builder is 
     * in a clean state where no cone is selected, toppings are disabled, and no ingredients can be added until a new 
     * cone is selected.
     */
    @Test
    void testResetBuilder() {
        builder.chooseCone(Conetype.CLASSIC);
        final Ingredient scoop = IngredientFactory.createIngredient(VANILLA);
        builder.addIngredient(scoop);

        // Reset the builder for a new ice cream
        builder.reset();

        assertFalse(builder.isToppingEnabled());
        assertFalse(builder.isClosed());
        assertFalse(builder.addIngredient(scoop)); // Should fail because no cone is selected
    }
}
