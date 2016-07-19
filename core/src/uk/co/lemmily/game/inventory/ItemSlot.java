package uk.co.lemmily.game.inventory;

import uk.co.lemmily.game.entity.ObjectType;

/**
 * Created by Emily on 23/10/2014.
 */
public class ItemSlot extends Slot {

    public ItemSlot(ObjectType item, int amount) {
        this.objectType = item;
        this.amount =  amount;
    }

    public ItemSlot(ObjectType item, int amount, int unit) {
        this.objectType = item;
        this.amount =  amount;
        this.units = unit;
    }
    public ItemSlot() {
        this(null, 0);
    }

    public boolean isEmpty() {
        return objectType == null || amount <= 0;
    }

    /**
     * add more of the same objectType type
     * @param amount
     */
    public boolean add(int amount) {
        if (this.objectType != null && getAmount() < getMaxAmount()) {
            this.amount += amount;
            notifyListeners();
            return true;
        }
        return false;
    }




    public ObjectType getObjectType() {
        return objectType;
    }

    @Override
    public ItemSlot copy() {
        return new ItemSlot(objectType, amount);
    }

    @Override
    public ItemSlot clear() {
        ItemSlot slot = this.copy();
        this.objectType = ObjectType.NOTHING;
        this.amount = 0;

        return slot;
    }

    public void empty() {
        objectType = null;
        amount = 0;
        notifyListeners();
    }

}
