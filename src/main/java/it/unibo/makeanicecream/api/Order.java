package it.unibo.makeanicecream.api;

import java.util.List;

public interface Order {

    List<Ingredient> getFlavors(); //Ingredient di type SCOOP
    Ingredient getCone(); //Ingrediente di type CONE
    List<Ingredient>getToppings(); //Ingredient con Type=LIQUID_TOPPING O SOLID_TOPPING
    boolean isSatisfiedBy(Icecream icecream); //verifica tra ordine e gelato
}