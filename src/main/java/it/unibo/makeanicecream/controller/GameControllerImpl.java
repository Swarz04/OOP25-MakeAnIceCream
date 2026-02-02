package it.unibo.makeanicecream.controller;

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
    public void startGame(int levelNumber) {
        this.game.start(levelNumber);
    }

    @Override
    public void handleInput(GameAction<?> action) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleInput'");
    }

    @Override
    public void updateGame(double deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGame'");
    }

}
