package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.GameView;

public class GameControllerImpl implements GameController {

    private Game game;
    private GameLoop gameLoop;
    //private GameView view;

    /**
     * Builds a new game controller provided a game model.
     *
     * @param game the implementation of the game model
     */
    public GameControllerImpl(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;
    }

    @Override
    public void setView(GameView view) {
        //this.view = view;
        //this.view.setController(this);
    }

    @Override
    public void handleInput(final Event action) {
        Command command;
        switch (action.getType()) {
            case START_LEVEL:
                command = new StartLevelCommand(this.game, this.gameLoop);
                break;
            case CHOOSE_CONE:
                command = new ChooseConeCommand(this.game);
                break;
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
                command = new PauseCommand(this.game, this.gameLoop);
                break;
            case RESUME:
                command = new ResumeCommand(this.game, this.gameLoop);
                break;
            case GO_TO_MENU:
                command = new GoToMenuCommand(this.game, this.gameLoop);
                break;
            default:
                throw new IllegalArgumentException("Unknown action type: " + action.getType());
        }

        command.execute();
    }

    @Override
    public void updateGame(final double deltaTime) {
        this.game.update(deltaTime);

        /*if (this.view != null) {
            this.view.update();
        }*/    
    }

    @Override
    public boolean isGamePlaying() {
        return this.game.getState() == GameState.PLAYING;
    }
}
