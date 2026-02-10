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

    private final Game game;

    /**
     * Constructs a new DeliverCommand.
     *
     * @param game the game instance used to deliver the ice cream 
     *             to the current customer
     */
    public DeliverCommand(final Game game) {
        this.game = game;
    }

    /**
     * Executes the command by delivering the ice cream
     * to the current customer.
     */
    @Override
    public void execute() {
        this.game.getPlayer().deliverIceCream(this.game.getCurrentLevel().getCurrentCustomer());
    }
}
