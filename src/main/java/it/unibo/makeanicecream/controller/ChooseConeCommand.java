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

    private final Game game;
    private final Conetype cone;

    /**
     * Constructs a new ChooseConeCommand.
     *
     * @param game the game instance where the cone will be selected
     * @param cone the type of cone chosen by the player
     */
    public ChooseConeCommand(final Game game, final Conetype cone) {
        this.game = game;
        this.cone = cone;
    }

    /**
     * Executes the command by setting the chosen cone for the player.
     */
    @Override
    public void execute() {
        game.getPlayer().chooseCone(this.cone);
    }
}
