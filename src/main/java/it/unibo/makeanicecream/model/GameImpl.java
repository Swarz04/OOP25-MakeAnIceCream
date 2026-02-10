package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.Ingredient;
import it.unibo.makeanicecream.api.Level;
import it.unibo.makeanicecream.api.Player;
import it.unibo.makeanicecream.model.level.LevelFactory;

/**
 * Implementation of the {@link Game} interface.
 */
public final class GameImpl implements Game {

    private Level currentLevel;
    private GameState state;
    private final Player player;

    /**
     * Constructs a new game in the MENU state with a new player.
     */
    public GameImpl() {
        this.state = GameState.MENU;
        this.player = new PlayerImpl();
        this.currentLevel = null;
    }

    @Override
    public void start(final int levelNumber) {
        if (levelNumber <= 0) {
            throw new IllegalArgumentException("Level number must be positive.");
        }
        this.currentLevel = LevelFactory.createLevel(levelNumber);
        this.state = GameState.PLAYING;
    }

    @Override
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public GameState getState() {
        return this.state;
    }

    @Override
    public boolean chooseCone(final Conetype cone) {
        return this.player.chooseCone(cone);
    }

    @Override
    public boolean addIngredient(final Ingredient ingredient) {
        return this.player.addIngredient(ingredient);
    }

    @Override
    public boolean deliverIceCream(final Customer customer) {
        return this.player.deliverIceCream(customer);
    }

    @Override
    public void cancelIceCream() {
        this.player.cancelIceCream();
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
    public void returnToMenu() {
        this.state = GameState.MENU;
        this.currentLevel = null;
    }

    @Override
    public boolean isGameOver() {
        return this.state == GameState.GAME_OVER;
    }

    @Override
    public boolean isPaused() {
        return this.state == GameState.PAUSED;
    }

    @Override
    public boolean isPlaying() {
        return this.state == GameState.PLAYING;
    }

    /**
     * Updates the game state based on the current level progress.
     * Sets the state to GAME_OVER if lives are exhausted,
     * or LEVEL_COMPLETED if all customers have been served.
     */
    private void checkLevelProgress() {
        if (this.currentLevel == null) {
            return;
        }
    
        if (this.currentLevel.getLives() <= 0) {
            this.state = GameState.GAME_OVER;
        } else if (!this.currentLevel.hasNextCustomer()) {
            this.state = GameState.LEVEL_COMPLETED;
        }
    }

    @Override
    public void update(final double deltaTime) {
        if (this.state != GameState.PLAYING || this.currentLevel == null) {
            return;
        }
        this.currentLevel.update(deltaTime);
        checkLevelProgress();
    }

}
