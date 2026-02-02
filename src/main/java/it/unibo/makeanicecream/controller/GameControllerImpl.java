package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameAction;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.model.GameActionImpl;

public class GameControllerImpl implements GameController {

    private Game game;

    /**
     * Builds a new game controller provided a game model.
     *
     * @param game the implementation of the game model
     */
    public GameControllerImpl(final Game game) {
        this.game = game;
    }

    @Override
    public void startGame(int levelNumber) {
        this.game.start(levelNumber);
    }

    @Override
    public void handleInput(GameAction<?> action) {
        Command command;
        switch ((GameActionImpl.Type) action.getType()) {
            case ADD_INGREDIENT:
                command = new AddIngredientCommand(this.game);
                break;
            case DELIVER:
                command = new DeliverCommand(this.game);
                break;
            case CANCEL:
                command = new CancelCommand(this.game);
                break;
            default:
                throw new IllegalArgumentException("Unknown action type: " + action.getType());
        }

        command.execute();
    }

    @Override
    public void updateGame(double deltaTime) {
        //this.game.update(deltaTime);
    }

}
