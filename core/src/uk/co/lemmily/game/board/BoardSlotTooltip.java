package uk.co.lemmily.game.board;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import uk.co.lemmily.game.inventory.ItemSlot;
import uk.co.lemmily.game.inventory.Slot;
import uk.co.lemmily.game.inventory.SlotListener;

/**
 * Created by Emily on 23/10/2014.
 */
public class BoardSlotTooltip extends Window implements SlotListener {
    private BoardSlot slot;
    private Skin skin;

    public BoardSlotTooltip(BoardSlot slot, Skin skin) {
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

        setTitle(slot.getAmount() + "x " + slot.getItem());
        clear();
        Label label = new Label("Super awesome description of " + slot.getItem(), skin);
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
