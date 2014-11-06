package uk.co.lemmily.game.new_board;

import uk.co.lemmily.game.entity.GameObject;
import uk.co.lemmily.game.inventory.SlotListener;
import uk.co.lemmily.game.inventory.Slot;

/**
 * Created by Emily on 31/10/2014.
 */
public class BoardSlot extends Slot {

    public BoardSlot() {
        this(null, 0);
    }

    public BoardSlot(Entity entity, int amount) {
        if (item == null) {
            this.item = GameObject.NOTHING;
            this.amount = 0;
        } else {
            this.item = item;
            this.amount = amount;
        }
    }

    @Override
    public GameObject getItem() {
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
    public boolean add(GameObject targetType, int targetAmount) {
        if ( item == targetType || targetAmount <= 0) {
            item = targetType;
            amount += amount;
            notifyListeners();
            return true;
        }
        return false;
    }

    @Override
    public BoardSlot copy() {
        return new BoardSlot(this.getItem(), this.getAmount());
    }

    @Override
    public BoardSlot clear() {
        BoardSlot slot = this.copy();
        this.item = GameObject.NOTHING;
        this.amount = 0;
        return slot;
    }

    private void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }
}
