package uk.co.lemmily.game.new_board;

import uk.co.lemmily.game.entity.Entity;
import uk.co.lemmily.game.inventory.SlotListener;
import uk.co.lemmily.game.ui.Slot;

/**
 * Created by Emily on 31/10/2014.
 */
public class BoardSlot extends Slot {

    public BoardSlot() {
        this(null, 0);
    }

    public BoardSlot(Entity item, int amount) {
        if (item == null) {
            this.item = Entity.NOTHING;
            this.amount = 0;
        } else {
            this.item = item;
            this.amount = amount;
        }
    }

    @Override
    public Entity getItem() {
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
    public boolean add(Entity targetType, int targetAmount) {
        if ( item == targetType || targetAmount <= 0) {
            item = targetType;
            amount += amount;
            notifyListeners();
            return true;
        }
        return false;
    }

    private void notifyListeners() {
        for (SlotListener slotListener : slotListeners) {
            slotListener.hasChanged(this);
        }
    }
}
