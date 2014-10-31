package uk.co.lemmily.game.new_board;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Emily on 31/10/2014.
 */
public class BoardSlotActor extends ImageButton {
    private final Skin skin;
    private final BoardSlot slot;

    public BoardSlotActor(Skin skin, BoardSlot boardSlot) {
        super(skin);
        this.slot = boardSlot;
        this.skin = skin;
        addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                slot.
            }
        });

    }
}
