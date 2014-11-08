package uk.co.lemmily.game.new_board;

import uk.co.lemmily.game.entity.GameObject;
import uk.co.lemmily.game.entity.ObjectType;
import uk.co.lemmily.game.inventory.Slot;

/**
 * Created by Emily on 31/10/2014.
 */
public class BoardSlot extends Slot {

    private GameObject object;

    public BoardSlot() {
        this(null, 0);
    }

    public BoardSlot(GameObject object, int amount) {
        if (object == null) {
            this.object = null;
            this.objectType = ObjectType.NOTHING;
            this.amount = 0;
        } else {
            this.object = object;
            this.objectType = object.getType();
            this.amount = amount;
        }
    }

    @Override
    public BoardSlot copy() {
        return new BoardSlot(this.getObject(), this.getAmount());
    }

    @Override
    public BoardSlot clear() {
        BoardSlot slot = this.copy();
        this.objectType = ObjectType.NOTHING;
        this.amount = 0;
        return slot;
    }

    public void put(GameObject object) {
        clear();
        this.object = object;
        this.objectType = object.getType();
    }

    public GameObject getObject() {
        return object;
    }

    @Override
    public ObjectType getObjectType() {
        return object != null ? object.getType() : objectType;
    }
}