package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

/**
 * Command that delivers the prepared ice cream to the current customer.
 * 
 * <p>
 * This command is executed when the player decides to deliver
 * the ice cream to the customer of the current level.
 * </p>
 */
public final class DeliverCommand implements Command {
    /**
     * Executes the command by delivering the ice cream
     * to the current customer.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.deliverIceCream(game.getCurrentLevel().getCurrentCustomer());
    }
}
