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

    private final Game game;

    /**
     * Constructs a new CancelCommand.
     *
     * @param game the game instance where the current ice cream will be canceled
     */
    public CancelCommand(final Game game) {
        this.game = game;
    }

    /**
     * Executes the command by canceling the current ice cream of the player.
     */
    @Override
    public void execute() {
        this.game.getPlayer().cancelIceCream();
    }
}
