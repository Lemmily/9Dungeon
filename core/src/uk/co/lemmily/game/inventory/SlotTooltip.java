package uk.co.lemmily.game.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
//import uk.co.lemmily.game.board.BoardSlot;


/**
 * Created by Emily on 23/10/2014.
 */
public class SlotTooltip extends Window implements SlotListener {
    private ItemSlot slot;
    private Skin skin;

    public SlotTooltip(ItemSlot slot, Skin skin) {
        super("Tooltip...", skin);
        this.slot = slot;
        this.skin = skin;
        hasChanged(slot);
        slot.addListener(this);
        setVisible(false);
    }

    @Override
    public void hasChanged(Slot slot) {
        if (slot.isEmpty()) {
            setVisible(false);
            return;
        }

        setTitle(slot.getAmount() + "x " + slot.getObjectType());
        clear();
        Label label = new Label("Super awesome description of " + slot.getObjectType(), skin);
        add(label);
        pack();
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(slot.isEmpty()) {
            super.setVisible(false);
        }
    }
}
