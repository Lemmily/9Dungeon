package uk.co.lemmily.game.board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import uk.co.lemmily.game.ui.DragAndDropLocked;

/**
 * Created by Emily on 29/10/2014.
 */
public class BoardActor extends Window {


    public BoardActor(Board board, DragAndDropLocked dragAndDrop, Skin skin) {
        super("World", skin);


        defaults().space(5);
        row().fillX().expandX();



        int i = 0;
        for (BoardSlot slot : board.getSlots()) {
            BoardSlotActor slotActor = new BoardSlotActor(skin, slot);
            dragAndDrop.addSource(new BoardSlotSource(slotActor));
            dragAndDrop.addTarget(new BoardSlotTarget(slotActor));

            add(slotActor);//.width(slotActor.getMinWidth()).height(slotActor.getMinHeight());

            i++;
            if (i % 3 == 0) {
                row();
            }
        }

        pack();
        setPosition(Gdx.graphics.getWidth() / 2 - this.getWidth() / 2, Gdx.graphics.getHeight() / 2 - (this.getHeight() / 2));
        setVisible(true);
        setMovable(false);
    }

    public void resize(int w, int h) {
        setPosition(w / 2 - this.getWidth() / 2, h / 2 - (this.getHeight()/2));


    }
}
