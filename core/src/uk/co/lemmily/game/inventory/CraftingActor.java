package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import uk.co.lemmily.game.entity.ObjectType;
import uk.co.lemmily.game.inventory.Recipe.Byproduct;

import java.util.HashMap;

public class CraftingActor extends Window{

    private HashMap<ObjectType, Float> byproducts; //current build up of byproducts.

    public CraftingActor(final Crafting crafting, Inventory inventory, DragAndDrop dragAndDrop, Skin skin) {
        super("Crafting", skin);

        byproducts = new HashMap<>(  );


        final TextButton closeButton = new TextButton("X", skin);
        closeButton.addListener(new HidingClickListener(this));
        getButtonTable().add(closeButton).height(getPadTop());

        setPosition(0, 100);
        defaults().space(8);
        row().fill().expandX();

        int i = 0;
        for (ItemSlot slot : crafting.getSlots()) {

            ItemSlotActor slotActor = new ItemSlotActor(skin, slot);
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
        final ItemSlotActor slotActor = new ItemSlotActor(skin, crafting.result);
        dragAndDrop.addSource(new SlotSource(slotActor));
        add(slotActor).left();

        final ItemSlotActor byproductSlotActor = new ItemSlotActor(skin, crafting.byproduct);
        dragAndDrop.addSource(new SlotSource(byproductSlotActor));
        add(byproductSlotActor).right();

        TextButton craftButton = new TextButton("Craft", skin);
        craftButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                System.out.println("PRESSED: " + crafting.getCurrentCraftingSequence());
                ItemSlot slot;

                while ((slot = crafting.getRecipeResult(crafting.getCurrentCraftingSequence())) != null) {
                    if (crafting.result.add(slot.getObjectType(), slot.getAmount()) >= 0) {
                        //fixme - make appropriate number of items for the amount of items present.
                        //fixme: make more clever

                        Byproduct lByp = crafting.getByproduct(crafting.getCurrentCraftingSequence(), true);
                        if(lByp != null)
                        {
                            if( byproducts.containsKey( lByp.getObjectType() ) )
                            {
                                byproducts.replace( lByp.getObjectType(), byproducts.get( lByp.getObjectType() ) + lByp.getAmount() );

                                //if there's equal to or more than 1 annd
                                if( byproducts.get( lByp.getObjectType() ) >= 1)
                                {
									if ( crafting.byproduct.add( lByp.getObjectType(), ( int ) (byproducts.get( lByp.getObjectType() ) / 1) ) >= 0 )
									{
										float amount = (float)(byproducts.get( lByp.getObjectType() ) - byproducts.get( lByp.getObjectType() ) / 1);
										float other_amount = byproducts.get( lByp.getObjectType() ) % 1;
										byproducts.replace( lByp.getObjectType(), amount);
									}
                                	else
                                    	byproductSlotActor.shake();
                                    	//can't place the byproduct cause it's full/occupied by different type.
                                }
                            }
                            else
                            {
                                byproducts.put( lByp.getObjectType(), lByp.getAmount() );
                            }
                        }



                        crafting.takeOneFromAll();
//                        crafting.clearSlots();
                    } else {
                        slotActor.shake();
                        break;
                        //todo: cant do it.

                    }
                }
//                ItemSlot slot = crafting.getRecipeResult(crafting.getCurrentCraftingSequence());
//                if (slot != null) {
//                    //remove all items from input slots.
//                    if (crafting.result.add(slot.getObjectType(), slot.getAmount()) >= 0) {
//                        //fixme - make appropriate number of items for the amount of items present.
//                        //fixme: make more clever
//                        crafting.clearSlots();
//                    } else {
//                        slotActor.shake();
//                        //todo: cant do it.
//                    }
//                }
            }
        });
//        craftButton.pad(10);
        add(craftButton).right();



//        row();


        pack();
        setVisible(false);
    }
}
