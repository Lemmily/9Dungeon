package uk.co.lemmily.game.new_board;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import uk.co.lemmily.game.LibgdxUtils;

/**
 * Created by Emily on 31/10/2014.
 */
public class BoardSlotActor extends ImageButton {
    private final Skin skin;
    private final BoardSlot slot;

    public BoardSlotActor(Skin skin, BoardSlot boardSlot) {
        super(createStyle(skin, boardSlot));
        this.slot = boardSlot;
        this.skin = skin;
//
//        addListener(new ClickListener() {
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.print("hii");
//            }
//        });
//
        TextureAtlas icons = LibgdxUtils.assets.get("icons/tiles.atlas", TextureAtlas.class);

//        clearChildren();
//        this.addActor(new Image(icons.findRegion("bg")));
//        this.addActor(new Image(icons.findRegion(slot.getItem().getTextureRegion())));
        pack();
    }

    private static ImageButtonStyle createStyle(Skin skin, BoardSlot slot) {
        TextureAtlas icons = LibgdxUtils.assets.get("icons/icons.atlas", TextureAtlas.class);
        TextureRegion image;
        if (slot.getItem() != null) {
            image = icons.findRegion(slot.getItem().getTextureRegion());
        } else {
            //special empty
            image = icons.findRegion("default");
        }

        ImageButtonStyle style = new ImageButtonStyle(skin.get(ButtonStyle.class));
        style.imageUp = new TextureRegionDrawable(image);
        style.imageDown = new TextureRegionDrawable(image);

        style.imageUp.setMinWidth(image.getRegionWidth() * 2);
        style.imageUp.setMinHeight(image.getRegionHeight() * 2);
        style.imageDown.setMinWidth(image.getRegionWidth() * 2);
        style.imageDown.setMinHeight(image.getRegionHeight() * 2);

        return style;
    }
}
