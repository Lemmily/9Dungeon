package uk.co.lemmily.game.new_board;

import uk.co.lemmily.game.entity.GameObject;
import uk.co.lemmily.game.entity.ObjectType;
import uk.co.lemmily.game.inventory.Slot;
import uk.co.lemmily.game.inventory.SlotListener;

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
            this.item = ObjectType.NOTHING;
            this.amount = 0;
        } else {
            this.object = object;
            this.item = object.getItem();
            this.amount = amount;
        }
    }


    public void put(GameObject object) {
        clear();
        this.object = object;
        this.item = object.getItem();
    }

    @Override
    public ObjectType getItem() {
        if (object != null) return object.getItem();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return item == null || amount <= 0;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public boolean take(int amount) {
        if (amount >= amount) {
            amount -= amount;
            if (amount <= 0) {
                item = null;
            }
            notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public boolean add(ObjectType targetType, int targetAmount) {
        if (item == targetType || targetAmount <= 0) {
            item = targetType;
            amount += amount;
            notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public BoardSlot copy() {
        return new BoardSlot(this.getObject(), this.getAmount());
    }

    @Override
    public BoardSlot clear() {
        BoardSlot slot = this.copy();
        this.item = ObjectType.NOTHING;
        this.amount = 0;
        return slot;
    }

    private void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }


    public GameObject getObject() {
        return object;
    }
}
