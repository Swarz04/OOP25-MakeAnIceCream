package it.unibo.makeanicecream.model;

import it.unibo.makeanicecream.api.GameAction;

/**
 * Implementation of the GameAction interface.
 */
public class GameActionImpl implements GameAction<GameActionImpl.Type>{

    /**
     * The possible types of actions the user can perform.
     */
    public enum Type {
        ADD_INGREDIENT,
        DELIVER,
        CANCEL
    }

    private Type type;

    public GameActionImpl(final Type type) {
        this.type = type;
    }

    @Override
    public Type getType() {
        return this.type;
    }
    
}
