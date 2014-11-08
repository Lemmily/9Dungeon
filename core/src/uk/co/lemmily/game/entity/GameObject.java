package uk.co.lemmily.game.entity;

/**
 * Created by Emily on 07/11/2014.
 *
 * Actual object in game, on the board.
 */
public abstract class GameObject {

    protected ObjectType objectType;

    public ObjectType getType() {
        return objectType;
    }

    public String toString() {
        return "" + objectType.getTextureRegion();
    }
}
