package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.GameAction;
import it.unibo.makeanicecream.api.GameActionType;

/**
 * Implementation of the GameAction interface.
 */
public class GameActionImpl implements GameAction {

    private final GameActionType type;

    public GameActionImpl(final GameActionType type) {
        this.type = type;
    }

    @Override
    public GameActionType getType() {
        return this.type;
    }
    
}
