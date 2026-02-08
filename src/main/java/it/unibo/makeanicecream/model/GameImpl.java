package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.Level;
import it.unibo.makeanicecream.api.Player;
import it.unibo.makeanicecream.api.Customer;
import it.unibo.makeanicecream.model.level.LevelFactory;

/**
 * Implementation of the {@limk Game} interface.
 */
public class GameImpl implements Game {

    private Level currentLevel;
    private GameState state;
    private Player player = new PlayerImpl();

    public GameImpl() {
        this.state = GameState.MENU;
    }

    @Override
    public void start(final int levelNumber) {
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
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void pause() {
        if (this.state == GameState.PLAYING) {
            this.state = GameState.PAUSED;
            /*Da migliorare una volta pronte le implementazione di Timer e Customer.*/
            /*Level level = getLevel();
            Customer c = level.getCurrentCustomer();
            if(c != null) c.getTimer().pause();*/
            if (this.currentLevel != null) {
                final Customer c = this.currentLevel.getCurrentCustomer();
                if (c != null) {
                    c.getTimer().pause();
                }
            }
        }
    }

    @Override
    public void resume() {
        if (this.state == GameState.PAUSED) {
            this.state = GameState.PLAYING;
            /*Da migliorare una volta pronte le implementazione di Timer e Customer.*/
            /*Level level = getLevel();
            Customer c = level.getCurrentCustomer();
            if(c != null) c.getTimer().resume();*/
            if (this.currentLevel != null) {
                final Customer c = this.currentLevel.getCurrentCustomer();
                if (c != null) {
                    c.getTimer().resume();
                }
            }
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

    private void updateGameState() {
        if (this.currentLevel.hasNextCustomer() && this.currentLevel.getLives() <= 0) {
        if (this.currentLevel == null) {
            return;
        }
        if (this.currentLevel.getLives() <= 0) {
            this.state = GameState.GAME_OVER;
        } else if (!this.currentLevel.hasNextCustomer()) {
            this.state = GameState.LEVEL_COMPLETED;
        }
    }

    /**
     * Metodo che aggiorna lo stato del gioco.
     * Questo metodo deve essere chiamato ad ogni tick del game loop, con
     * il tempo trascorso dall'ultimo aggiornamento passato come parametro.
     * Delega poi l'aggiornamento al livello corrente, che si occupa di gestire
     * correttamente clienti e timer.
     * NOTA: Level e Customer dovrebbero avere a loro volta un metodo update(deltaTime)
     * che si occupa di decrementare il timer del cliente e gestire eventuali
     * perdite di vita o passaggi al cliente successivo.
     */
    public void update(final double deltaTime) {
        if (this.state != GameState.PLAYING || this.currentLevel == null) {
            return;
        }

        if (this.currentLevel.hasNextCustomer()) {
        if (this.state == GameState.PLAYING && this.currentLevel != null) {
            this.currentLevel.update(deltaTime);
            updateGameState();
        }

        updateGameState();
    }

}
