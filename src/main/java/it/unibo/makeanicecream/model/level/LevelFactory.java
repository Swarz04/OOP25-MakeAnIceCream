package it.unibo.makeanicecream.model.level;


import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Level;

import java.util.ArrayDeque;
import java.util.Queue;
import it.unibo.makeanicecream.model.customermodel.CustomerFactory;

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

        final double levelTime = Math.max(10.0, 60.0 - (difficulty * 5.0));

        final Queue<Customer> customers = new ArrayDeque<>();
        final CustomerFactory customerFactory = new CustomerFactory();

        for (int i = 0; i < numberOfCustomers; i++) {
            customers.add(customerFactory.createCustomer(limitedDifficulty, levelTime));
        }

        return new StandardLevel(limitedDifficulty, LIVES, customers);
    }
 }
