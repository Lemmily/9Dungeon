package uk.co.lemmily.game.board;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import uk.co.lemmily.game.LibgdxUtils;
import uk.co.lemmily.game.entity.Entity;
import uk.co.lemmily.game.ui.DragAndDropLocked;
import uk.co.lemmily.game.ui.DragAndDropLocked.Payload;
import uk.co.lemmily.game.ui.DragAndDropLocked.Target;
import uk.co.lemmily.game.ui.Slot;

/**
 * Created by Emily on 23/10/2014.
 */
public class BoardSlotSource extends DragAndDropLocked.Source {
    private BoardSlot sourceSlot;

    public BoardSlotSource(BoardSlotActor actor) {
        super(actor);
        this.sourceSlot = actor.getSlot();
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        if (sourceSlot.getAmount() <= 0) {
            return null;
        }

        Payload payload = new Payload();
        BoardSlot payloadSlot = new BoardSlot(sourceSlot.getItem(), sourceSlot.getAmount());
        sourceSlot.take(sourceSlot.getAmount());
        payload.setObject(payloadSlot);

        TextureAtlas icons = LibgdxUtils.assets.get("icons/tiles.atlas", TextureAtlas.class);
        TextureRegion icon = icons.findRegion(payloadSlot.getItem().getTextureRegion());
        Actor dragActor = new Image(icon);
        payload.setDragActor(dragActor);

        Actor validDragActor = new Image(icon);
        payload.setDragActor(validDragActor);


        Actor invalidDragActor = new Image(icon);
        payload.setDragActor(invalidDragActor);

        return payload;
    }

    public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        Slot payloadSlot = (BoardSlot) payload.getObject();

        if (target != null) {
            BoardSlot targetSlot =((BoardSlotActor) target.getActor()).getSlot();
            //if item is the same, stack it.
            if (targetSlot.getItem() == payloadSlot.getItem() || targetSlot.getItem() == null) {
                targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
            } else {
                //item in slot is not the same, so switch items.
                Entity targetType = targetSlot.getItem();
                int targetAmount = targetSlot.getAmount();
                targetSlot.take(targetAmount);
                targetSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
                sourceSlot.add(targetType, targetAmount);
            }
        } else {
            //invalid drop location, put it back to whence it came!
            sourceSlot.add(payloadSlot.getItem(), payloadSlot.getAmount());
        }
    }
}
