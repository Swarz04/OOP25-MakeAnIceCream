package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Game;

/**
 * Command that allows the player to choose a cone type for the ice cream.
 * 
 * <p>
 * This command is executed when the user selects a specific cone.
 * </p>
 */
public final class ChooseConeCommand implements Command {

    private final Conetype cone;

    /**
     * Constructs a new ChooseConeCommand.
     *
     * @param cone the type of cone chosen by the player
     */
    public ChooseConeCommand(final Conetype cone) {
        this.cone = cone;
    }

    /**
     * Executes the command by setting the chosen cone for the player.
     * 
     * @param game the game instance on which the command should be executed
     */
    @Override
    public void execute(final Game game) {
        game.chooseCone(this.cone);
    }
}
