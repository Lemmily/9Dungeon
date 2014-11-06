package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.GameObject;

/**
 * Created by Emily on 29/10/2014.
 */
public abstract class Slot {

    protected GameObject item;
    protected int amount;
    protected int maxAmount;
    protected Array<SlotListener> slotListeners = new Array<SlotListener>();


    public abstract GameObject getItem();

    public abstract boolean isEmpty();

    public int getAmount() {
        return amount;
    }
    public int getMaxAmount() {
        return maxAmount;
    }

    public abstract boolean take(int amount);

    public abstract boolean add(GameObject targetType, int targetAmount);

    public String toString() {
        return "Slot[" + item + ":" + amount + "]";
    };

    public abstract Slot copy();

    public abstract Slot clear();
}