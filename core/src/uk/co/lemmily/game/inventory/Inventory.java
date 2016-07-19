package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.ObjectType;

/**
 * Created by Emily on 23/10/2014.
 */
public class Inventory {

    private Array<ItemSlot> slots;


    public Inventory() {
        slots = new Array<>(25);

        //create the empty slots
        for (int i = 0; i < 25; i++) {
            slots.add(new ItemSlot(null, 0));
        }
//        Entity defaultEntity = new Entity();
        ObjectType en = new ObjectType();
        //create some random items
        for (ItemSlot slot : slots) {
            ObjectType e = (ObjectType) ObjectType.getItems().values().toArray()[MathUtils.random(10, ObjectType.getItems().values().size() - 1)];
            if( ! e.getCategory().equals("resource")){
                continue;
            }
            slot.add(e, MathUtils.random(1, e.getMaxNum()));
            if (slot.getObjectType() == ObjectType.NOTHING) {
                slot.clear();
            }
        }

        //empty some of the slots
        for (int i = 0; i < 3; i++) {
             ItemSlot randomSlot = slots.get(MathUtils.random(0, slots.size - 1));
            randomSlot.take(randomSlot.getAmount());
        }

    }

    /**
     *
     * @param item type to check for
     * @return amount of objectType type present.
     */
    public int checkInventory(ObjectType item) {
        int amount = 0;

        for (ItemSlot slot : slots) {
            if ( slot.getObjectType() == item) {
                amount += slot.getAmount();
            }
        }

        return amount;
    }

    public boolean store( ObjectType item, int amount) {
        ItemSlot itemSlot = firstSlotWithItem(item);

        if (itemSlot != null ) {
            itemSlot.add(item, amount);
            return true;
        } else {
            ItemSlot emptySlot = firstSlotWithItem(null);
            if ( emptySlot != null) {
                emptySlot.add(item, amount);
                return true;
            }
        }
        return false;
    }

    public void sort() {
        //TODO: sort items into stacks of their own type.
    }



    public Array<ItemSlot> getSlots() {
        return slots;
    }

    private ItemSlot firstSlotWithItem(ObjectType item) {
        for (ItemSlot slot : slots) {
            if (slot.getObjectType() == item) {
                return slot;
            }
        }

        return null;
    }

}
