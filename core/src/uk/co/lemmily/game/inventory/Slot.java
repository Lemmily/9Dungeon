package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.ObjectType;

/**
 * Created by Emily on 29/10/2014.
 */
public abstract class Slot {

    protected ObjectType objectType;
    protected int amount;
    protected Array<SlotListener> slotListeners = new Array<SlotListener>();


    public ObjectType getObjectType() {
        return objectType;
    }


    public int getAmount() {
        return amount;
    }

    public int getMaxAmount() {
        if (objectType != null)   return objectType.getMaxNum();
        else                return 0;
    }

    public boolean take(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            if (this.amount <= 0) {
                objectType = null;
            }
            notifyListeners();
            return true;
        }
        return false;
    }

    public int add(ObjectType item, int amount) {

        int num = -1;
        if ((this.objectType == item || this.objectType == null)) {
            this.objectType = item;
            if (getAmount() + amount < getMaxAmount())
                this.amount += amount;
            else {
                num = this.amount + amount;
                this.amount = getMaxAmount();
                num = num % getMaxAmount();
            }
            notifyListeners();
            num = 0;
        }

        // num values: -1 UNSUCCESSFUL, 0 COMPLETE SUCCESS, 0 < SUCCESS WITH LEFTOVERS
        return  num;
    }


    public String toString() {
        return "Slot[" + objectType + ":" + amount + "]";
    };

    public abstract Slot copy();

    public abstract Slot clear();


    public boolean isEmpty() {
        return objectType == null || amount <= 0;
    }



    public void addListener(SlotListener slotListener) {
        slotListeners.add(slotListener);
    }

    public void removeListener(SlotListener slotListener) {
        slotListeners.removeValue(slotListener, true);
    }

    protected void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }
}
