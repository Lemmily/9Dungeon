package uk.co.lemmily.game.inventory;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import uk.co.lemmily.game.LibgdxUtils;
import uk.co.lemmily.game.screens.GameScreen;
import uk.co.lemmily.game.tween.ActorAccessor;

/**
 * Created by Emily on 23/10/2014.
 */
public class SlotActor extends ImageTextButton implements SlotListener {


    private final Skin skin;
    private final ItemSlot slot;

    public SlotActor(Skin skin, ItemSlot slot) {
        super(String.valueOf(slot.getAmount()), createStyle(skin, slot));
        this.slot = slot;
        this.skin = skin;
        this.setSize(this.getWidth() * 2, this.getHeight() * 2);
        hasChanged(slot);
        slot.addListener(this);

        SlotTooltip tooltip = new SlotTooltip(slot, skin);
        GameScreen.stage.addActor(tooltip);
        addListener(new ToolTipListener(tooltip, true));

        clearChildren();
        add(getImage());
        row();
        add(getLabel());
    }

    private static ImageTextButtonStyle createStyle(Skin skin, Slot slot) {
        TextureAtlas icons = LibgdxUtils.assets.get("icons/resources.atlas", TextureAtlas.class);
        TextureRegion image;
        if (slot.getObjectType() != null) {
            image = icons.findRegion(slot.getObjectType().getTextureRegion());
        } else {
            //special empty
            image = icons.findRegion("default");
        }

        ImageTextButtonStyle style = new ImageTextButtonStyle(skin.get(ImageTextButtonStyle.class));
        style.imageUp = new TextureRegionDrawable(image);
        style.imageDown = new TextureRegionDrawable(image);

        style.imageUp.setMinWidth(image.getRegionWidth() * 2);
        style.imageUp.setMinHeight(image.getRegionHeight() * 2);
        style.imageDown.setMinWidth(image.getRegionWidth() * 2);
        style.imageDown.setMinHeight(image.getRegionHeight() * 2);

        return style;
    }

    public ItemSlot getSlot() {
         return slot;
    }

    @Override
    public void hasChanged(Slot slot) {
//        this.clearChildren();
        setStyle(createStyle(skin, slot));
        if(slot.isEmpty()) {
            setText(" ");
        } else {
            setText(String.valueOf(slot.getAmount()));
        }
        this.setSize(this.getWidth()*2, this.getHeight()*2);
//        Label label = new Label("" + this.slot.getAmount(), skin);
//        label.setWidth(20);
//        label.setHeight(20);
//        this.add(label).bottom().right();
//        pack();
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
