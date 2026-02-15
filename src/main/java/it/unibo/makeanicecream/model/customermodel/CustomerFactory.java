package it.unibo.makeanicecream.model.customermodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.FlavorType;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.LiquidToppingType;
import it.unibo.makeanicecream.api.Order;
import it.unibo.makeanicecream.api.SolidToppingType;
import it.unibo.makeanicecream.model.OrderBuilder;
import it.unibo.makeanicecream.model.ingredient.IngredientType;
import it.unibo.makeanicecream.model.ingredient.LiquidTopping;
import it.unibo.makeanicecream.model.ingredient.Scoop;
import it.unibo.makeanicecream.model.ingredient.SolidTopping;

/**
 * Factory for generating random customers based on difficulty levels.
 * creates customer with randomly generated orders. Time is determined by game Level.
 */
public class CustomerFactory {
    private static final int MAX_DIFFICULTY = 5;
    private final Map<Integer, CustomerTemplate> templates = new HashMap<>();
    private final List<FlavorType> availableFlavors;
    private final List<Conetype> availableCones;
    private final List<Ingredient> availableToppings;
    private final Random random = new Random();

    /**
     * Constructs a CustomerFacory with all the available ingredients from enums.
     * 
     */
    public CustomerFactory() {
        this.availableFlavors = Arrays.asList(FlavorType.values());
        this.availableCones = Arrays.asList(Conetype.values());
        this.availableToppings = createAllToppings();

        initializeTemplates();
    }

    /**
     * Creates all posible topping ingredients from enum values.
     * 
     * @return list containing all possible topping ingredients.
     */
    private List<Ingredient> createAllToppings() {
        final List<Ingredient> toppings = new ArrayList<>();

        for (final LiquidToppingType liquidType : LiquidToppingType.values()) {
            toppings.add(new LiquidTopping(liquidType));
        }

        for (final SolidToppingType solidType: SolidToppingType.values()) {
            toppings.add(new SolidTopping(solidType));
        }
        return toppings;
    }

    /**
     * Inizializes the difficulty templates with predifined configurations.
     * 
     */
    private void initializeTemplates() {
        templates.put(1, new CustomerTemplate(new String[]{"Maria", "Paolo"}, 1, 0));
        templates.put(2, new CustomerTemplate(new String[]{"Giulia"}, 2, 0));
        templates.put(3, new CustomerTemplate(new String[]{"Giorgio"}, 3, 0));
        templates.put(4, new CustomerTemplate(new String[]{"Lucia"}, 3, 1));
        templates.put(MAX_DIFFICULTY, new CustomerTemplate(new String[]{"Mario"}, 3, 2));

    }

    /**
     * Creates a new random coustumer with the maxdifficulty of the level.
     * 
     * @param maxDifficulty the maximum difficulty level (1-5).
     * @param levelTime the seconds the timer's client have in the specific level.
     * @return a new Customer with a random generated order.
     */
    public Customer createCustomer(final int maxDifficulty, final double levelTime) {
        if (maxDifficulty < 1 || maxDifficulty > MAX_DIFFICULTY) {
            throw new IllegalArgumentException("La difficoltà deve essere tra 1  e 5");
        }
        if (levelTime <= 0) {
            throw new IllegalArgumentException("Il tempo del livello deve essere positivo: " + levelTime);
        }

        final int difficulty = random.nextInt(maxDifficulty) + 1;

        final CustomerTemplate template = templates.get(difficulty);
        if (template == null) {
            throw new IllegalStateException("Template non trovato per difficolta: " + difficulty);
        }

        final String name = template.getNextName();
        final Order order = createRandomOrder(template);
        final CustomerTimer timer = new CustomerTimer(levelTime);

        return new CustomerImpl(name, order, timer, difficulty);
    }

    /**
     * Creates a random order based on template specifications.
     * 
     * @param template the template defining the order requirements.
     * @return a new Order with random ingredients.
     */
    private Order createRandomOrder(final CustomerTemplate template) {
        final OrderBuilder builder = new OrderBuilder();

        for (int i = 0; i < template.getScoopCount(); i++) {
            builder.addFlavor(createRandomFlavor());
        }

        builder.setCone(getRandomCone());

        final int toppingCount = template.getToppingCount();

        if (toppingCount > 0) {
            int solidtoppingCount = (toppingCount >= 2) ? 1 : 0;
            int liquidtoppingCount = toppingCount - solidtoppingCount;
            
            for (int i = 0; i < liquidtoppingCount; i++) {
                Ingredient topping;
                do {
                    topping = getRandomTopping();
                } while (topping.getType() != IngredientType.LIQUID_TOPPING);
                builder.addTopping(topping);
            }

            for (int i = 0; i < solidtoppingCount; i++) {
                Ingredient topping;
                do {
                    topping = getRandomTopping();
                } while (topping.getType() != IngredientType.SOLID_TOPPING);
                builder.addTopping(topping);
            }
        }
        return builder.build();
    }

    /**
     * Creates a random Scoop Ingredient.
     * 
     * @return a random flavor scoop.
     */
    private Ingredient createRandomFlavor() {
        final FlavorType randomFlavor = availableFlavors.get(random.nextInt(availableFlavors.size()));
        return new Scoop(randomFlavor);
    }

    /**
     * Gets a random cone type.
     * 
     * @return a random cone type.
     */
    private Conetype getRandomCone() {
        return availableCones.get(random.nextInt(availableCones.size()));
    }

    /**
     * Gets a random topping ingredient.
     * 
     * @return a random topping ingredient.
     */
    private Ingredient getRandomTopping() {
        return availableToppings.get(random.nextInt(availableToppings.size()));
    }

    /**
     * Returns a string representation of this factory.
     * 
     * @return String.format("CustomerFactory[flavors=%d, cones=%d, toppings=%d").
     */
    @Override
    public String toString() {
        return String.format("CustomerFactory[flavors=%d, cones=%d, toppings=%d]",
            availableFlavors.size(), availableCones.size(), availableToppings.size());
    }
}
