package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

/**
 * Command that cancels the current ice cream being prepared by the player.
 * 
 * <p>
 * This command is executed by the game controller when the user wants
 * to discard the current ice cream.
 * </p>
 */
public final class CancelCommand implements Command {
    /**
     * Executes the command by canceling the current ice cream of the player.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.cancelIceCream();
    }
}
