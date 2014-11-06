package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.entity.GameObject;

/**
 * Created by Emily on 23/10/2014.
 */
public class Inventory {

    private Array<ItemSlot> slots;


    public Inventory() {
        slots = new Array<ItemSlot>(25);

        //create the empty slots
        for (int i = 0; i < 25; i++) {
            slots.add(new ItemSlot(null, 0));
        }
//        Entity defaultEntity = new Entity();
        GameObject en = new GameObject();
        //create some random items
        for (ItemSlot slot : slots) {
            GameObject e = (GameObject) GameObject.getItems().values().toArray()[MathUtils.random(0, GameObject.getItems().values().size() - 1)];
            slot.add(e, MathUtils.random(1, e.getMaxNum()));
            if (slot.getItem() == GameObject.NOTHING) {
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
     * @return amount of item type present.
     */
    public int checkInventory(GameObject item) {
        int amount = 0;

        for (ItemSlot slot : slots) {
            if ( slot.getItem() == item) {
                amount += slot.getAmount();
            }
        }

        return amount;
    }

    public boolean store( GameObject item, int amount) {
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

    }



    public Array<ItemSlot> getSlots() {
        return slots;
    }

    private ItemSlot firstSlotWithItem(GameObject item) {
        for (ItemSlot slot : slots) {
            if (slot.getItem() == item) {
                return slot;
            }
        }

        return null;
    }

}
