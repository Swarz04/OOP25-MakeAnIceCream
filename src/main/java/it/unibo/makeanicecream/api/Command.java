package it.unibo.makeanicecream.api;

/**
 * Represents a command that can be executed by the game controller.
 * <p>
 * This interface is part of the Command pattern used in the controller to
 * encapsulate user actions and decouple them from the controller logic.
 * </p>
 */
@FunctionalInterface
public interface Command {
    
    /**
     * Executes the command.
     * 
     * @param game the game instance on which the command should be executed
     */
    void execute(Game game);
}
