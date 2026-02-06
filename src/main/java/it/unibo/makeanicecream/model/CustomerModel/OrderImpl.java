package it.unibo.makeanicecream.model.customermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.model.IngredientImpl;
import it.unibo.makeanicecream.model.ingredient.Conetype;
import it.unibo.makeanicecream.model.ingredient.IngredientType;

/**
 * Implementation of the Order Interface representing a customer's ice cream order
 * 
 * An order consist of flavors (scoops), a cone, and optional toppings
 * This class can verify if an IceCream satisfies the order requirements
 *  
 */
public class OrderImpl implements Order {
    
    private final List<Ingredient> requiredFlavors;
    private final Conetype requiredCone;
    private final List<Ingredient> requiredToppings;
    
    /**
     * Constructs a new Order with the specified components
     * 
     * @param Flavors the list of flavor scoops required (can't be empty)
     * @param cone the cone required (can't be null)
     * @param toppings the list of toppings required (can be empty)
     */
    public OrderImpl(List<Ingredient> flavors, Conetype cone, List<Ingredient>toppings){
        validateConstructorArguments(flavors, cone, toppings);
        this.requiredFlavors = new ArrayList<>(flavors);
        this.requiredCone = cone;
    }
    /**
     * Validares constructor arguments
     */
    private void validateConstructorArguments(List<Ingredient> flavors, Conetype cone, List<Ingredient> toppings){
        if(flavors == null || flavors.isEmpty()){
            throw new IllegalArgumentException("L'ordine deve contenere almeno un gusto");
        }

        for(Ingredient flavor : flavors){
            if(flavor.getType() != IngredientType.SCOOP){
                throw new IllegalArgumentException("I flavor devono essere di tipo scoop");
            }
        }

        if(cone == null){
            throw new IllegalArgumentException("L'ordine deve specificare un cono");
        }

        if(toppings !=null){
            for(Ingredient topping: toppings)
                if(topping.getType() != IngredientType.LIQUID_TOPPING && topping.getType() != IngredientType.SOLID_TOPPING){
                    throw new IllegalArgumentException("i toppings devono essere LIQUID o SOLID");
                }
            }
        }
    }

    /**
     * Gets the list of flavor scoops required by this order
     *
     * @return an unmodifiable view of the required flavors
     */
    
    @Override
    public List<Ingredient> getFlavors() {
        return Collections.unmodifiableList(requiredFlavors);
    }
    
    /**
     * Gets the cone required by this order
     * 
     * @return the requiredCone 
     */
    @Override
    public Conetype getConeType() {
        return requiredCone;
    }

    /**
     * Gets the list of toppings required by this order
     *
     *  @return an unmodifiable view of the required toppings
     */
    @Override
    public List<Ingredient> getToppings() {
        return Collections.unmodifiableList(requiredToppings);
    }

    /**
     * Verifies if the provided ice cream satisfies this order.
     * The ice cream must contain all required flavors, the cone, and all toppings
     * 
     * @param iceCream the ice cream to check against this order
     * @return true if the ice cream satisfies all the order requirements, false otherwise
     */
    @Override
    public boolean isSatisfiedBy(Icecream icecream) {
        Objects.requireNonNull(icecream, "L'ice cream non puo essere null");

        List<Ingredient> iceCreamIngredients=icecream.getIngredients();

        
    }
    
    /**
     * Helper: gets all required ingredients (flavors + cone + toppings)
     */
    private List<Ingredient>getAllRequiredIngredients(){
        List<Ingredient> all = new ArrayList<>();
        all.addAll(requiredFlavors);
        all.add(requiredCone);
        all.addAll(requiredToppings);

        return all;
    }

    /**
     * Helper: check if list contains an equal ingredient
     */
    private boolean containsIngredient(List<Ingredient> ingredients, Ingredient toFind){
        for(Ingredient ingredient: ingredients){
            //if(ingredientsEqual(ingredient, toFind)){
                return true;
            }
        }


    /**
     * Helper: compare two ingredients
     */
    /*private boolean ingredientsEqual(Ingredient a, Ingredient b){
        return a.getName().equals(b.getName()) && a.getType() == b.getType();
    }*/
}
