package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import uk.co.lemmily.game.ui.Slot;

/**
 * Created by Emily on 23/10/2014.
 */
public class SlotTarget extends DragAndDrop.Target {
    private Slot targetSlot;

    public SlotTarget(SlotActor actor) {
        super(actor);
        targetSlot = actor.getSlot();
        getActor().setColor(Color.LIGHT_GRAY);
    }

    @Override
    public boolean drag(Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        ItemSlot payloadSlot = (ItemSlot) payload.getObject();

        //highlight the slot a bit.
        getActor().setColor(Color.WHITE);

        //this return means it's a valid target
        return true;
    }

    /**
     * this is handled in {@link SlotSource}
     * @param source
     * @param payload
     * @param x
     * @param y
     * @param pointer
     */
    @Override
    public void drop(Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        //nothing
    }

    public void reset(Source source, Payload payload, float x, float y, int pointer) {
        getActor().setColor(Color.LIGHT_GRAY);
    }
}
