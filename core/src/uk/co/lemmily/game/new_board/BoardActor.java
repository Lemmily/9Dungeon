package uk.co.lemmily.game.new_board;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import uk.co.lemmily.game.board.BoardSlotActor;

/**
 * Created by Emily on 30/10/2014.
 */
public class BoardActor extends Window {
    public BoardActor(Board board, DragAndDrop dragAndDrop, Skin skin) {
        super("Main World", skin);

        int i = 0;
        for (Array<BoardSlot> slots : board.getSlots()) {
            for (int j = 0; j < slots.size; j++) {
                BoardSlot slot = slots.get(j);
                BoardSlotActor slotActor = new Boar
                add(slot);
            }
        }
    }
}
