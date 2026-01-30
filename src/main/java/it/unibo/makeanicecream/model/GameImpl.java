package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.Level;
import it.unibo.makeanicecream.model.level.LevelFactory;

/**
 * Implementation of the {@limk Game} interface.
 */
public class GameImpl implements Game {

    private Level currentLevel;
    private GameState state;
    
    @Override
    public void start(final int levelNumber) {
        this.currentLevel = LevelFactory.createLevel(levelNumber, null);
        this.state = GameState.PLAYING;
    }

    @Override
    public Level getLevel() {
        return this.currentLevel;
    }

    @Override
    public void pause() {
        if (this.state == GameState.PLAYING) {
            this.state = GameState.PAUSED;
        }
    }

    @Override
    public void resume() {
        if (this.state == GameState.PAUSED) {
            this.state = GameState.PLAYING;
        }
    }

    @Override
    public boolean isGameOver() {
        return this.state == GameState.GAME_OVER;
    }

    private void updateGameState() {
        if (this.currentLevel.hasNextCustomer() && this.currentLevel.getLives() <= 0) {
            this.state = GameState.GAME_OVER;
        } else if (!this.currentLevel.hasNextCustomer()) {
            this.state = GameState.LEVEL_COMPLETED;
        }
    }

}
