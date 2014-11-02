package uk.co.lemmily.game.board;

import uk.co.lemmily.game.entity.Entity;
import uk.co.lemmily.game.inventory.SlotListener;
import uk.co.lemmily.game.ui.Slot;

/**
 * Created by Emily on 29/10/2014.
 */
public class BoardSlot extends Slot {


    public BoardSlot(Entity entity) {
        this(entity, 1);
    }

    public BoardSlot(Entity entity, int amount) {
        if (entity == null) {
            this.item = Entity.NOTHING;
            this.amount = 0;
        } else {
            this.item = entity;
            this.amount = amount;
        }
    }

    @Override
    public Entity getItem() {
        return item;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean take(int amount) {
        return false;
    }

    @Override
    public boolean add(Entity targetType, int targetAmount) {
        if ( this.item == targetType || this.item == null) {
            this.item = targetType;
            this.amount += amount;
            notifyListeners();
            return true;
        }
        return  false;
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
}
