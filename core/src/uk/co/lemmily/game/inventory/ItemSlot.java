package uk.co.lemmily.game.inventory;

import uk.co.lemmily.game.entity.ObjectType;

/**
 * Created by Emily on 23/10/2014.
 */
public class ItemSlot extends Slot {

    public ItemSlot(ObjectType item, int amount) {
        this.item = item;
        this.amount =  amount;
    }

    public ItemSlot() {
        this(null, 0);
    }

    public boolean isEmpty() {
        return item == null || amount <= 0;
    }

    /**
     * add more of the same item type
     * @param amount
     */
    public boolean add(int amount) {
        if (this.item != null && getAmount() < getMaxAmount()) {
            this.amount += amount;
            notifyListeners();
            return true;
        }
        return false;
    }

    public boolean add(ObjectType item, int amount) {
        if ((this.item == item || this.item == null) && getAmount() < getMaxAmount()) {
            this.item = item;
            this.amount += amount;
            notifyListeners();
            return true;
        }
        return  false;
    }

    public boolean take(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            if (this.amount <= 0) {
                item = null;
            }
            notifyListeners();
            return true;
        }
        return false;
    }

    public void addListener(SlotListener slotListener) {
        slotListeners.add(slotListener);
    }

    public void removeListener(SlotListener slotListener) {
        slotListeners.removeValue(slotListener, true);
    }

    private void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }

    public ObjectType getItem() {
        return item;
    }

    @Override
    public ItemSlot copy() {
        return new ItemSlot(item, amount);
    }

    @Override
    public ItemSlot clear() {
        ItemSlot slot = this.copy();
        this.item = ObjectType.NOTHING;
        this.amount = 0;

        return slot;
    }

    public void empty() {
        item = null;
        amount = 0;
        notifyListeners();
    }

}
