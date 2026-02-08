package it.unibo.makeanicecream.model.level;

<<<<<<< HEAD
import it.unibo.makeanicecream.api.customer.Customer;
=======
import it.unibo.makeanicecream.api.Customer;
>>>>>>> 303bf0bf8df103d08e73d8626e641f90a4c721ca
import it.unibo.makeanicecream.api.Level;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/**
 * Factory class for creating Level instances.
 * Levels are created based on the specified difficulty
 * and a queue of customers.
 */
public final class LevelFactory {

    private static final int MIN_DIFFICULTY = 1;
    private static final int MAX_DIFFICULTY = 5;

    private static final int MAX_CUSTOMERS = 15;
    private static final int BASE_CUSTOMERS = 7;
    private static final int STEP_CUSTOMERS = 2;

    private static final int LIVES = 3;
    
    private LevelFactory() {
    }

    /**
     *
     * Creates a level based on the given difficulty.
     * @param difficulty the difficulty level (1-5)
     * @param customers the customers queue
     *
     * @return a new Level instance
     */
    public static Level createLevel(final int difficulty) {

        final int limitedDifficulty = Math.min(Math.max(difficulty, MIN_DIFFICULTY),MAX_DIFFICULTY);

        final int numberOfCustomers = Math.min(MAX_CUSTOMERS, BASE_CUSTOMERS + STEP_CUSTOMERS*(limitedDifficulty - 1));

       /*
        * for (int i = 0; i < numberOfCustomers; i++) {
        * final Customer c = createCustomer(limitedDifficulty); // da sostituire (Customer studente 1)
        * customers.add(c)
        * TODO: integrate Customer creation.
        * For each customer index in [0, numberOfCustomers),
        * create a Customer instance configured with the given difficulty
        * and add it to the queue.
        */
        final Queue<Customer> customers = new ArrayDeque<>();
        
        return new StandardLevel(limitedDifficulty, LIVES, customers);
    }
 }
