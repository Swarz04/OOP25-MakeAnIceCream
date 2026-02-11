package it.unibo.makeanicecream.controller;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.makeanicecream.api.Command;
import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.Event;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameLoop;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.GameView;

/**
 * Implementation of the {@link GameController} interface.
 */
public final class GameControllerImpl implements GameController {

    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP", 
        justification = "The game reference is safely shared with commands and is immutable from outside"
    )
    private final Game game;
    private final GameLoop gameLoop;
    private GameView view;
    private final Map<EventType, Function<Event, Command>> commands = new EnumMap<>(EventType.class);

    /**
     * Builds a new game controller provided a game model.
     *
     * @param game the implementation of the game model
     * @param gameLoop the implementation of the game loop
     */
    public GameControllerImpl(final Game game, final GameLoop gameLoop) {
        this.game = game;
        this.gameLoop = gameLoop;

        this.commands.put(EventType.START_LEVEL, event -> {
            final int levelNumber = Integer.parseInt(event.getData());
            return new StartLevelCommand(this.gameLoop, levelNumber);
        });
        this.commands.put(EventType.CHOOSE_CONE, event -> {
            final Conetype cone = Conetype.valueOf(event.getData());
            return new ChooseConeCommand(cone);
        });
        this.commands.put(EventType.ADD_INGREDIENT, event -> new AddIngredientCommand(event.getData()));
        this.commands.put(EventType.DELIVER, event -> new DeliverCommand());
        this.commands.put(EventType.CANCEL, event -> new CancelCommand());
        this.commands.put(EventType.PAUSE, event -> new PauseCommand(this.gameLoop));
        this.commands.put(EventType.RESUME, event -> new ResumeCommand(this.gameLoop));
        this.commands.put(EventType.GO_TO_MENU, event -> new GoToMenuCommand(this.gameLoop));

    }

    @Override
    public void setView(final GameView view) {
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void handleInput(final Event event) {
        final Function<Event, Command> commandFactory = this.commands.get(event.getType());

        if (commandFactory == null) {
            throw new IllegalArgumentException("Unknown action type: " + event.getType());
        }

        commandFactory.apply(event).execute(this.game);
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

    @Override
    public GameState getGameState() {
        return this.game.getState();
    }
}
