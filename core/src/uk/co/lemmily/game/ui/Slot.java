package uk.co.lemmily.game.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.Entity;
import uk.co.lemmily.game.inventory.SlotListener;

/**
 * Created by Emily on 29/10/2014.
 */
public abstract class Slot {

    protected Entity item;
    protected int amount;
    protected Array<SlotListener> slotListeners = new Array<SlotListener>();


    public abstract Entity getItem();

    public abstract boolean isEmpty();

    public abstract int getAmount();

    public abstract boolean take(int amount);

    public abstract boolean add(Entity targetType, int targetAmount);

    public String toString() {
        return "Slot[" + item + ":" + amount + "]";
    };
}
