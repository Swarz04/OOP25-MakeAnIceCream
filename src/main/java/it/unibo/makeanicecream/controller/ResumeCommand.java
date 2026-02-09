package it.unibo.makeanicecream.controller;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Game;

public class ResumeCommand implements Command{
    private Game game;

    public ResumeCommand(final Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        this.game.resume();
    }
}
