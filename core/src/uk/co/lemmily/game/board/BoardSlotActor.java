package uk.co.lemmily.game.board;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import uk.co.lemmily.game.LibgdxUtils;
import uk.co.lemmily.game.screens.GameScreen;
import uk.co.lemmily.game.inventory.SlotListener;
import uk.co.lemmily.game.inventory.ToolTipListener;
import uk.co.lemmily.game.tween.ActorAccessor;
import uk.co.lemmily.game.ui.Slot;

/**
 * Created by Emily on 23/10/2014.
 */
public class BoardSlotActor extends ImageButton implements SlotListener {


    private final Skin skin;
    private final BoardSlot slot;

    public BoardSlotActor(Skin skin, BoardSlot slot) {
        super(createStyle(skin, slot));
        this.slot = slot;
        this.skin = skin;

        defaults().space(5);
        slot.addListener(this);
//        scaleBy(2f);


        BoardSlotTooltip tooltip = new BoardSlotTooltip(slot, skin);
        GameScreen.stage.addActor(tooltip);
        addListener(new ToolTipListener(tooltip, true));
//        pack();

    }

    private static ImageButtonStyle createStyle(Skin skin, BoardSlot slot) {
        TextureAtlas icons = LibgdxUtils.assets.get("icons/tiles.atlas", TextureAtlas.class);
        TextureRegion image;
        if (slot.getItem() != null) {
            image = icons.findRegion(slot.getItem().getTextureRegion());
        } else {
            //special empty
            image = icons.findRegion("nothing");
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

    public BoardSlot getSlot() {
         return slot;
    }

    @Override
    public void hasChanged(Slot slot) {
//        this.clearChildren();
        setStyle(createStyle(skin, (BoardSlot)slot));
//        if(slot.isEmpty()) {
//            setText(" ");
//        } else {
//            setText(String.valueOf(slot.getAmount()));
//        }
//        this.setSize(this.getWidth()*2, this.getHeight()*2);
    }


    public void shake() {
        int x = (int)getX();
        Timeline.createSequence().beginSequence()
//                .push(Tween.set(this, ActorAccessor.X).target(0))
                .push(Tween.from(this, ActorAccessor.X, 0.2f).target(x))
                .push(Tween.to(this, ActorAccessor.X, 0.1f).target(x - 3))
                .push(Tween.to(this, ActorAccessor.X, 0.2f).target(x + 3))
                .push(Tween.to(this, ActorAccessor.X, 0.1f).target(x))
                .end().start(LibgdxUtils.tweenManager);
    }
}
