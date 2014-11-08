package uk.co.lemmily.game.entity;

/**
 * Created by Emily on 06/11/2014.
 */
public class ItemDrop extends GameObject {

    public ItemDrop(ObjectType item) {
        this.objectType = item;
    }

    @Override
    public ObjectType getType() {
        return objectType;
    }
}
