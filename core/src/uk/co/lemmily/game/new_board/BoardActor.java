package uk.co.lemmily.game.new_board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.LibgdxUtils;
import uk.co.lemmily.game.entity.Player;

/**
 * Created by Emily on 30/10/2014.
 */
public class BoardActor extends Window {

    public BoardActor(Board board, final Player player, DragAndDrop dragAndDrop, Skin skin) {
        super("Main World", skin);

//        int i = 0;
        for (Array<BoardSlot> slots : board.getSlots()) {
            for (int j = 0; j < slots.size; j++) {
                final BoardSlot slot = slots.get(j);
                final BoardSlotActor slotActor = new uk.co.lemmily.game.new_board.BoardSlotActor(skin, slot);
                slotActor.addListener(new ClickListener() {
                    @Override
                    public void clicked (InputEvent event, float x, float y) {
                        int btn = event.getButton();
                        if (btn == Input.Buttons.LEFT) {
                            //todo: link player interaction
                            player.interact(slot.getItem());
                        } else {
                            //todo: link to updating display of ui? HOW DOES THIS WORK ARGH
                            LibgdxUtils libgdxUtils = (LibgdxUtils) Gdx.app.getApplicationListener();
                            libgdxUtils.getUIelement().updateInfoPanel(slot);
                        }
                    }
                });
                add(slotActor);
            }
            row();
        }
        pack();

    }

    public void resize(int width, int height) {

    }
}
