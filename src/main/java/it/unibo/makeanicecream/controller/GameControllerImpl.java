package it.unibo.makeanicecream.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;
import it.unibo.makeanicecream.api.GameView;

public class GameControllerImpl implements GameController {

    private final Game game;
    private final GameLoop gameLoop;
    private GameView view;
    private final Map<EventType, Function<Event, Command>> commands = new HashMap<>();

    /**
     * Builds a new game controller provided a game model.
     *
     * @param game the implementation of the game model
     */
    public GameControllerImpl(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;

        this.commands.put(EventType.START_LEVEL, event -> {
            final int levelNumber = Integer.parseInt(event.getData());
            return new StartLevelCommand(this.game, this.gameLoop, levelNumber);
        });
        this.commands.put(EventType.CHOOSE_CONE, event -> {
            final Conetype cone = Conetype.valueOf(event.getData());
            return new ChooseConeCommand(this.game, cone);
        });
        this.commands.put(EventType.ADD_INGREDIENT, event -> new AddIngredientCommand(this.game, event.getData()));
        this.commands.put(EventType.DELIVER, event -> new DeliverCommand(this.game));
        this.commands.put(EventType.CANCEL, event -> new CancelCommand(this.game));
        this.commands.put(EventType.PAUSE, event -> new PauseCommand(this.game, this.gameLoop));
        this.commands.put(EventType.RESUME, event -> new ResumeCommand(this.game, this.gameLoop));
        this.commands.put(EventType.GO_TO_MENU, event -> new GoToMenuCommand(this.game, this.gameLoop));

    }

    @Override
    public void setView(GameView view) {
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void handleInput(final Event event) {
        final Function<Event, Command> commandFactory = this.commands.get(event.getType());

        if (commandFactory == null) {
            throw new IllegalArgumentException("Unknown action type: " + event.getType());
        }

        commandFactory.apply(event).execute();
    }

    @Override
    public void updateGame(final double deltaTime) {
        this.game.update(deltaTime);

        if (this.view != null) {
            this.view.update();
        } 
    }

    @Override
    public boolean isGamePlaying() {
        return this.game.isPlaying();
    }
}
