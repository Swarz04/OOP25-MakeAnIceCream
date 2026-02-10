package it.unibo.makeanicecream.api;

public interface GameEngine {
    GameController getController();

    void start();

    void stop();
}
