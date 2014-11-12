package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

/**
 * Created by Emily on 23/10/2014.
 */
public class InventoryActor extends Window {
    public InventoryActor(Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
        super("Inventory", skin);

        TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        getButtonTable().add(closeButton).height(getPadTop());

        setPosition(400, 100);
        defaults().space(8);
        row().fill().expandX();

        int i = 0;
        for (ItemSlot slot : inventory.getSlots()) {
            //create the "drag and drop"
            ItemSlotActor slotActor = new ItemSlotActor(skin, slot);
            dragAndDrop.addSource(new SlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));

            //add the slot to the inventoy
            add(slotActor);

            i++;
            // every 5 cells, we are going to jump to a new row
            if (i % 5 == 0) {
                row();
            }
        }

        pack();

        //hidden by default.
        setVisible(false);
    }
}
