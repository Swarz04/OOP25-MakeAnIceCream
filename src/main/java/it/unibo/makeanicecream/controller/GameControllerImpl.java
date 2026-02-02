package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameAction;
import it.unibo.makeanicecream.api.GameController;

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
    public void startGame(final int levelNumber) {
        this.game.start(levelNumber);
    }

    @Override
    public void handleInput(final GameAction action) {
        Command command;
        switch (action.getType()) {
            case ADD_INGREDIENT:
                command = new AddIngredientCommand(this.game);
                break;
            case DELIVER:
                command = new DeliverCommand(this.game);
                break;
            case CANCEL:
                command = new CancelCommand(this.game);
                break;
            case PAUSE:
                command = new PauseCommand(this.game);
                break;
            case RESUME:
                command = new ResumeCommand(this.game);
                break;
            case GO_TO_MENU:
                command = new GoToMenuCommand(this.game);
                break;
            default:
                throw new IllegalArgumentException("Unknown action type: " + action.getType());
        }

        command.execute();
    }

    @Override
    public void updateGame(final double deltaTime) {
        //this.game.update(deltaTime);
    }

}
