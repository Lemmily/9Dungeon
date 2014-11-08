package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

/**
 * Created by Emily on 24/10/2014.
 */
public class CraftingActor extends Window{
//    private final Crafting crafting;

    public CraftingActor(final Crafting crafting, Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
        super("Crafting", skin);
        final TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        getButtonTable().add(closeButton).height(getPadTop());

        setPosition(0, 100);
        defaults().space(8);
        row().fill().expandX();

        int i = 0;
        for (ItemSlot slot : crafting.getSlots()) {

            SlotActor slotActor = new SlotActor(skin, slot);
            dragAndDrop.addSource(new SlotSource(slotActor));
            dragAndDrop.addTarget(new SlotTarget(slotActor));
            add(slotActor);

            i++;
            if (i % crafting.modulo == 0) {
                row(); //.fill().expandX();
            }

        }

        row();
        add();
        row();

        //out put slot.
        final SlotActor slotActor = new SlotActor(skin, crafting.result);
        dragAndDrop.addSource(new SlotSource(slotActor));
        add(slotActor).left();

        TextButton craftButton = new TextButton("Craft", skin);
        craftButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                System.out.println("PRESSED: " + crafting.getCraftingSequence());
                ItemSlot slot = crafting.getRecipe(crafting.getCraftingSequence());
                if (slot != null) {
                    //remove all items from input slots.
                    if (crafting.result.add(slot.getObjectType(), slot.getAmount()) >= 0) {
                        //fixme - make appropriate number of items for the amount of items present.
                        //fixme: make more clever
                        crafting.clearSlots();
                    } else {
                        slotActor.shake();
                        //todo: cant do it.
                    }
                }
            }
        });
//        craftButton.pad(10);
        add(craftButton).right();

        add();



//        row();


        pack();
        setVisible(false);
    }
}
