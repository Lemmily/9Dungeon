package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import uk.co.lemmily.game.LibgdxUtils;
import uk.co.lemmily.game.entity.ObjectType;

/**
 * Created by Emily on 23/10/2014.
 */
public class SlotSource extends DragAndDrop.Source {
    private ItemSlot sourceSlot;

    public SlotSource(SlotActor actor) {
        super(actor);
        this.sourceSlot = actor.getSlot();
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        if (sourceSlot.getAmount() <= 0) {
            return null;
        }

        DragAndDrop.Payload payload = new Payload();
        ItemSlot payloadSlot;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            payloadSlot = new ItemSlot(sourceSlot.getObjectType(), 1);
            sourceSlot.take(1);
        } else {

            payloadSlot = new ItemSlot(sourceSlot.getObjectType(), sourceSlot.getAmount());
            sourceSlot.take(sourceSlot.getAmount());
        }
        payload.setObject(payloadSlot);

        TextureAtlas icons = LibgdxUtils.assets.get("icons/resources.atlas", TextureAtlas.class);
        TextureRegion icon = icons.findRegion(payloadSlot.getObjectType().getTextureRegion());
        Actor dragActor = new Image(icon);
        payload.setDragActor(dragActor);

        Actor validDragActor = new Image(icon);
        payload.setDragActor(validDragActor);


        Actor invalidDragActor = new Image(icon);
        payload.setDragActor(invalidDragActor);

        return payload;
    }

    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        ItemSlot payloadSlot = (ItemSlot) payload.getObject();

        if (target != null) {
            ItemSlot targetSlot =((SlotActor) target.getActor()).getSlot();
            //if objectType is the same, stack it.
            if (targetSlot.getObjectType() == payloadSlot.getObjectType() || targetSlot.getObjectType() == null) {
                targetSlot.add(payloadSlot.getObjectType(), payloadSlot.getAmount());
            } else {
                //objectType in slot is not the same, so switch items.
                ObjectType targetType = targetSlot.getObjectType();
                int targetAmount = targetSlot.getAmount();
                targetSlot.take(targetAmount);
                targetSlot.add(payloadSlot.getObjectType(), payloadSlot.getAmount());
                sourceSlot.add(targetType, targetAmount);
            }
        } else {
            //invalid drop location, put it back to whence it came!
            sourceSlot.add(payloadSlot.getObjectType(), payloadSlot.getAmount());
        }
    }
}
