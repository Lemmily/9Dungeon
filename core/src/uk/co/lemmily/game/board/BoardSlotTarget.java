package uk.co.lemmily.game.board;

import com.badlogic.gdx.graphics.Color;
import uk.co.lemmily.game.ui.DragAndDropLocked.Payload;
import uk.co.lemmily.game.ui.DragAndDropLocked.Source;
import uk.co.lemmily.game.ui.DragAndDropLocked.Target;
import uk.co.lemmily.game.inventory.ItemSlot;
import uk.co.lemmily.game.ui.Slot;

/**
 * Created by Emily on 23/10/2014.
 */
public class BoardSlotTarget extends Target {
    private BoardSlot targetSlot;

    public BoardSlotTarget(BoardSlotActor actor) {
        super(actor);
        targetSlot = actor.getSlot();
        getActor().setColor(Color.LIGHT_GRAY);
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        Slot payloadSlot = (BoardSlot) payload.getObject();

        //highlight the slot a bit.
        getActor().setColor(Color.WHITE);

        //this return means it's a valid target
        return true;
    }

    /**
     * this is handled in {@link BoardSlotSource}
     * @param source
     * @param payload
     * @param x
     * @param y
     * @param pointer
     */
    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        //nothing
    }

    public void reset(Source source, Payload payload, float x, float y, int pointer) {
        getActor().setColor(Color.LIGHT_GRAY);
    }
}
