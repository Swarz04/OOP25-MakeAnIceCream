package it.unibo.makeanicecream.model.CustomerModel;
;
import java.util.List;

import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Order;

public class OrderImpl implements Order {
    
    private final List<Ingredient> requiredFlavors;
    private final Ingredient Cone;
    private final List<Ingredient> requiredToppings;
    
    public OrderImpl(List<Ingredient>Flavors, Ingredient cone, List<Ingredient>toppings){
        this.requiredFlavors = new Arraylist<>(flavors);
        this.requiredCone = cone;
        this.requiredToppings = new ArrayList<>(toppings);
    }
    @Override
    public List<Ingredient> getFlavors() {
        
    }

    @Override
    public Ingredient getCone() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCone'");
    }

    @Override
    public List<Ingredient> getToppings() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToppings'");
    }

    @Override
    public boolean isSatisfiedBy(Icecream icecream) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSatisfiedBy'");
    }
    
}
