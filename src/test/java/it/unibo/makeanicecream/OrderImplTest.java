package it.unibo.makeanicecream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.FlavorType;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.LiquidToppingType;
import it.unibo.makeanicecream.api.SolidToppingType;
import it.unibo.makeanicecream.model.IceCreamImpl;
import it.unibo.makeanicecream.model.customermodel.OrderImpl;
import it.unibo.makeanicecream.model.ingredient.*;

/**
 * Test per la classe OrderImpl.
 * Verifica la corretta costruzione degli ordini.
 * Verifica la validazione degli argomenti e il metodo isSatisfiedBy.
 */
public class OrderImplTest {
    private Ingredient vanillaScoop;
    private Ingredient chocolateScoop;
    private Ingredient liquidChocolate;
    private Ingredient liquidStrawberry;
    private Ingredient solidCherry;
    private Ingredient solidCookies;
    private Conetype flowerCone;
    private Conetype classicCone;

    @BeforeEach
    void setUp() {
        vanillaScoop = new Scoop(FlavorType.VANILLA);
        chocolateScoop = new Scoop(FlavorType.CHOCOLATE);
        liquidChocolate = new LiquidTopping(LiquidToppingType.CHOCOLATE_SYRUP);
        liquidStrawberry = new LiquidTopping(LiquidToppingType.STRAWBERRY_SYRUP);
        solidCherry = new SolidTopping(SolidToppingType.CHERRY);
        solidCookies = new SolidTopping(SolidToppingType.COOKIES);
        classicCone = Conetype.CLASSIC;
        flowerCone = Conetype.FLOWER;
    }

    /**
     * Test that the constructor throws exceptions for invalid arguments. 
     */
    @Test
    void testConstructorValidation() {
        assertThrows(IllegalArgumentException.class,
            () -> new OrderImpl(null, classicCone, new ArrayList<>()));
        assertThrows(IllegalArgumentException.class,
            () -> new OrderImpl(new ArrayList<>(), classicCone, new ArrayList<>())); 
        assertThrows(IllegalArgumentException.class,
            () -> new OrderImpl(List.of(vanillaScoop), null, new ArrayList<>())); 
        assertThrows(IllegalArgumentException.class,
            () -> new OrderImpl(List.of(liquidChocolate), classicCone, new ArrayList<>()));
        assertThrows(IllegalArgumentException.class,
            () -> new OrderImpl(List.of(vanillaScoop), classicCone, List.of(vanillaScoop)));
        assertThrows(IllegalArgumentException.class,
            () -> new OrderImpl(List.of(vanillaScoop), classicCone, List.of(solidCherry, solidCookies))); 
    }
    
    /**
     * Tests the creation of a minimal order that is valid.
     */
    @Test
    void testValidMinimalOrder() {
        Order order = new OrderImpl(List.of(vanillaScoop), classicCone, null);
        
        assertEquals(1, order.getFlavors().size());
        assertEquals(vanillaScoop, order.getFlavors().get(0));
        assertEquals(classicCone, order.getRequestedConeType());
        assertTrue(order.getToppings().isEmpty());
    }
    
    /**
     * Tests the creation of a complex order with multiple flavors and toppings.
     */
    @Test
    void testValidComplexOrder() {
        List <Ingredient> flavors = List.of(vanillaScoop, chocolateScoop);
        List <Ingredient> toppings = List.of(liquidChocolate, solidCherry);

        Order order = new OrderImpl(flavors, flowerCone, toppings);

        assertEquals(2, order.getFlavors().size());
        assertEquals(vanillaScoop, order.getFlavors().get(0));
        assertEquals(chocolateScoop, order.getFlavors().get(1));
        assertEquals(flowerCone, order.getRequestedConeType());
        assertEquals(2, order.getToppings().size());
        assertEquals(liquidChocolate, order.getToppings().get(0));
        assertEquals(solidCherry, order.getToppings().get(1));
    }

    /**
     * Test that returned lists are unmodifiable. 
     */
    @Test
    void testUnmodifiableLists(){
      Order order = new OrderImpl(List.of(vanillaScoop), classicCone, List.of(liquidChocolate));  
      assertThrows(UnsupportedOperationException.class,
        () -> order.getFlavors().add(chocolateScoop));
      assertThrows(UnsupportedOperationException.class,
        () -> order.getToppings().add(solidCherry));
    }

    /**
     * Tests that an identical ice cream satisfies the order.
     */
    @Test
    void testIsSatisfiedByMatchingIceCream() {
        Order order = new OrderImpl(List.of(vanillaScoop), classicCone, List.of(liquidChocolate));
        List<Ingredient> iceCreamIngredients = Arrays.asList(vanillaScoop, liquidChocolate);
        Icecream matchingIcecream = new IceCreamImpl(classicCone, iceCreamIngredients, false);

        assertTrue(order.isSatisfiedBy(matchingIcecream));
    }

    /**
     * Test that an ice cream with different cone is rejected.
     */
    @Test
    void testIsSatisfiedByWrongCone(){
        Order order = new OrderImpl(List.of(vanillaScoop), classicCone, new ArrayList<>());
        List<Ingredient> ingredients = List.of(vanillaScoop);
        Icecream wrongConeIcecream = new IceCreamImpl(flowerCone, ingredients, false);
        
        assertFalse(order.isSatisfiedBy(wrongConeIcecream));
    }

    /**
     * Tests that an ice cream with the wrong number of ingredients is rejected.
     */
    @Test
    void testIsSatisfiedByWrongIngredientCount() {
        Order order = new OrderImpl(List.of(vanillaScoop, chocolateScoop),
        classicCone, List.of(liquidChocolate));
        
        List <Ingredient> tooFew = List.of(vanillaScoop, liquidChocolate);
        Icecream tooFewIceCream = new IceCreamImpl(classicCone, tooFew, false);
        assertFalse(order.isSatisfiedBy(tooFewIceCream));
        
        List <Ingredient> tooMany = List.of(vanillaScoop, chocolateScoop, liquidChocolate, solidCherry);
        Icecream tooManyIceCream = new IceCreamImpl(classicCone, tooMany, true);
        assertFalse(order.isSatisfiedBy(tooManyIceCream));
    }

    /**
     * Test that the order flavors matters.
     */
    @Test
    void testIsSatisfiedByWrongOrder() {
        Order order = new OrderImpl(List.of(vanillaScoop, chocolateScoop),
        classicCone, List.of(liquidChocolate));
        
        List<Ingredient> wrongOrder = List.of(chocolateScoop, vanillaScoop, liquidChocolate);
        Icecream wrongOrderIcecream = new IceCreamImpl(classicCone, wrongOrder, false);
        assertFalse(order.isSatisfiedBy(wrongOrderIcecream));
    }
    
    /**
     * Test that the order of toppings matters.
     */
    @Test
    void testIsSatisfiedByToppingsWrongOrder() {
        Order order = new OrderImpl(List.of(vanillaScoop, chocolateScoop),
        classicCone, List.of(liquidChocolate, solidCherry));
        
        List<Ingredient> wrongOrder = Arrays.asList(vanillaScoop, solidCherry, liquidChocolate);
        Icecream wrongOrderIcecream = new IceCreamImpl(classicCone, wrongOrder, true);
        assertFalse(order.isSatisfiedBy(wrongOrderIcecream));
    }

    /**
     * Tests that an order with only liquid topping is valid and order is respected.
     */
    @Test
    void testAllLiquidToppings() {
        Order order = new OrderImpl(List.of(vanillaScoop, chocolateScoop),
        classicCone, List.of(liquidChocolate, liquidStrawberry));
        
        List<Ingredient> ingredients = Arrays.asList(vanillaScoop,chocolateScoop, liquidChocolate, liquidStrawberry);
        Icecream iceCream = new IceCreamImpl(classicCone, ingredients, false);
        
        assertTrue(order.isSatisfiedBy(iceCream));
    }

    /**
     * Test the string representation of the order.
     * Nota: richiede che le classi degli ingredienti implementino toString() in modo significativo.
     */
    @Test
    void testToString() {
        Order order = new OrderImpl(List.of(vanillaScoop), classicCone, List.of(liquidChocolate));
        String toString = order.toString();
        assertTrue(toString.contains("VANILLA"));
        assertTrue(toString.contains("CLASSIC"));
        assertTrue(toString.contains("CHOCOLATE_SYRUP"));
    }
}   
