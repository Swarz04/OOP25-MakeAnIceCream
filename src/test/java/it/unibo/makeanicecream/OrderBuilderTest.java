package it.unibo.makeanicecream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.FlavorType;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.LiquidToppingType;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.api.SolidToppingType;
import it.unibo.makeanicecream.model.customermodel.OrderBuilder;
import it.unibo.makeanicecream.model.ingredient.Scoop;
import it.unibo.makeanicecream.model.ingredient.LiquidTopping;
import it.unibo.makeanicecream.model.ingredient.SolidTopping;

/**
 * Test for OrderBuilder class
 * Verifies correct order construction via builder pattern.
 */
class OrderBuilderTest {
    private OrderBuilder builder;
    private Ingredient vanillaScoop;
    private Ingredient chocolateScoop;
    private Ingredient liquidChocolate;
    private Ingredient solidCherry;

    @BeforeEach
    void setUp() {
        builder = new OrderBuilder();
        vanillaScoop = new Scoop(FlavorType.VANILLA);
        chocolateScoop = new Scoop(FlavorType.CHOCOLATE);
        liquidChocolate = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        solidCherry = new SolidTopping(SolidToppingType.CHERRY);
    }

    /**
     * Tests the initial empty state of the builder.
     */
    @Test
    void testInitialState() {
        final String toString = builder.toString();
        assertTrue(toString.contains("flavors=0"));
        assertTrue(toString.contains("cone=null"));
        assertTrue(toString.contains("toppings=0"));
    }

    /**
     * Tests validation of added flavors.
     */
    @Test
    void testAddFlavorValidation() {
        assertThrows(IllegalArgumentException.class,
            () -> builder.addFlavor(null));

        assertThrows(IllegalArgumentException.class,
            () -> builder.addFlavor(liquidChocolate));
    }

    /**
     * Tests validation of cone setting.
     */
    @Test
    void testSetConeValidation() {
        assertThrows(IllegalArgumentException.class,
            () -> builder.setCone(null));
    }

    /**
     * Tests validation of topping addition.
     */
    @Test
    void testAddToppingValidation() {
        assertThrows(IllegalArgumentException.class,
            () -> builder.addTopping(null));

        assertThrows(IllegalArgumentException.class,
            () -> builder.addTopping(vanillaScoop));
    }

    /**
     * Tests building a minimal valid order.
     */
    @Test
    void testBuildMinimalOrder() {
        final Order order = builder
            .addFlavor(vanillaScoop)
            .setCone(Conetype.CLASSIC)
            .build();

        assertNotNull(order);
        assertEquals(1, order.getFlavors().size());
        assertEquals(vanillaScoop, order.getFlavors().get(0));
        assertEquals(Conetype.CLASSIC, order.getRequestedConeType());
        assertTrue(order.getToppings().isEmpty());
    }

    /**
     * Tests building a complex order with multiple flavors and toppings.
     */
    @Test
    void testBuildComplexOrder() {
        final Order order = builder
            .addFlavor(vanillaScoop)
            .addFlavor(chocolateScoop)
            .setCone(Conetype.FLOWER)
            .addTopping(liquidChocolate)
            .addTopping(solidCherry)
            .build();

        assertEquals(2, order.getFlavors().size());
        assertEquals(vanillaScoop, order.getFlavors().get(0));
        assertEquals(chocolateScoop, order.getFlavors().get(1));
        assertEquals(Conetype.FLOWER, order.getRequestedConeType());
        assertEquals(2, order.getToppings().size());
        assertEquals(liquidChocolate, order.getToppings().get(0));
        assertEquals(solidCherry, order.getToppings().get(1));
    }

    /**
     * Tests that builder methods support method chaining.
     */
    @Test
    void testMethodChaining() {
        final OrderBuilder returned = builder
            .addFlavor(vanillaScoop)
            .setCone(Conetype.CLASSIC)
            .addTopping(liquidChocolate);

        assertSame(builder, returned);
    }

    /**
     * Tests that building without a cone is not allowed.
     */
    @Test
    void testBuildWithoutCone() {
        builder.addFlavor(vanillaScoop);

        assertThrows(IllegalStateException.class,
            builder::build);
    }

    /**
     * Tests that building without flavors is not allowed.
     */
    @Test
    void testBuildWithoutFlavors() {
        builder.setCone(Conetype.CLASSIC);

        assertThrows(IllegalStateException.class,
            builder::build);
    }

    /**
     * Tests that adding two liquid toppings is allowed.
     */
    @Test
    void testTwoLiquidToppings() {
        final Ingredient liquidStrawberry = new LiquidTopping(LiquidToppingType.STRAWBERRY_SYRUP);

        final Order order = builder
            .addFlavor(vanillaScoop)
            .setCone(Conetype.CLASSIC)
            .addTopping(liquidChocolate)
            .addTopping(liquidStrawberry)
            .build();

        assertEquals(2, order.getToppings().size());
    }

    /**
     * Tests that adding one solid and one liquid is allowed.
     */
    @Test
    void testSolidAndLiquid() {
        final Order order = builder
            .addFlavor(vanillaScoop)
            .setCone(Conetype.CLASSIC)
            .addTopping(liquidChocolate)
            .addTopping(solidCherry)
            .build();

        assertEquals(2, order.getToppings().size());
        assertEquals(liquidChocolate, order.getToppings().get(0));
        assertEquals(solidCherry, order.getToppings().get(1));
    }

    /**
     * Tests that reset returns the builder to the initial empty state.
     */
    @Test
    void testReset() {
        builder
            .addFlavor(vanillaScoop)
            .addFlavor(chocolateScoop)
            .setCone(Conetype.CLASSIC)
            .addTopping(liquidChocolate);

        final OrderBuilder resetBuilder = builder.reset();

        assertSame(builder, resetBuilder);

        assertThrows(IllegalStateException.class,
            builder::build);

        builder.addFlavor(vanillaScoop);
        assertThrows(IllegalStateException.class,
            builder::build);
    }

    /**
     * Tests the string representation of the current builder state.
     */
    @Test
    void testToString() {
        builder
            .addFlavor(vanillaScoop)
            .setCone(Conetype.CLASSIC)
            .addTopping(liquidChocolate);

        final String toString = builder.toString();
        assertTrue(toString.contains("flavors=1"));
        assertTrue(toString.contains("cone=CLASSIC"));
        assertTrue(toString.contains("toppings=1"));
    }

    /**
     * Tests that multiple independent orders can be built using reset.
     */
    @Test
    void testMultipleOrders() {
        final Order order1 = builder
            .addFlavor(vanillaScoop)
            .setCone(Conetype.CLASSIC)
            .build();

        builder.reset();

        final Order order2 = builder
            .addFlavor(chocolateScoop)
            .addFlavor(vanillaScoop)
            .setCone(Conetype.FLOWER)
            .addTopping(solidCherry)
            .build();

        assertEquals(1, order1.getFlavors().size());
        assertEquals(2, order2.getFlavors().size());
        assertEquals(Conetype.CLASSIC, order1.getRequestedConeType());
        assertEquals(Conetype.FLOWER, order2.getRequestedConeType());
        assertTrue(order1.getToppings().isEmpty());
        assertEquals(1, order2.getToppings().size());
    }
}
