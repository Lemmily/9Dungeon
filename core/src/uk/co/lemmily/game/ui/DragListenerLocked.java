package uk.co.lemmily.game.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
//import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/**
 * Created by Emily on 30/10/2014.
 */
public class DragListenerLocked extends InputListener {
    private float tapSquareSize = 14, touchDownX = -1, touchDownY = -1, stageTouchDownX = -1, stageTouchDownY = -1;
    private int pressedPointer = -1;
    private int button;
    private boolean dragging;
    private float deltaX, deltaY;
    boolean xAxis = false;
    private boolean yAxis = false;


    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        if (pressedPointer != -1) return false;
        if (pointer == 0 && this.button != -1 && button != this.button) return false;
        pressedPointer = pointer;
        touchDownX = x;
        touchDownY = y;
        stageTouchDownX = event.getStageX();
        stageTouchDownY = event.getStageY();
        return true;
    }

    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        if (pointer != pressedPointer) return;
        if (!dragging && (Math.abs(touchDownX - x) > tapSquareSize || Math.abs(touchDownY - y) > tapSquareSize)) {
            dragging = true;
            dragStart(event, x, y, pointer);
            deltaX = x;
            deltaY = y;
            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                xAxis = true;
            } else {
                yAxis = true;
            }
        }
        if (dragging) {
            deltaX -= x;
            deltaY -= y;
            drag(event, x, y, pointer);
            deltaX = x;
            deltaY = y;
        }
    }

    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        if (pointer == pressedPointer) {
            if (dragging) dragStop(event, x, y, pointer);
            cancel();
        }
    }


    public void dragStart (InputEvent event, float x, float y, int pointer) {
    }

    public void drag (InputEvent event, float x, float y, int pointer) {
    }

    public void dragStop (InputEvent event, float x, float y, int pointer) {
    }

    /* If a drag is in progress, no further drag methods will be called until a new drag is started. */
    public void cancel () {
        xAxis = false;
        yAxis = false;
        dragging = false;
        pressedPointer = -1;
    }

    /** Returns true if a touch has been dragged outside the tap square. */
    public boolean isDragging () {
        return dragging;
    }

    public void setTapSquareSize (float halfTapSquareSize) {
        tapSquareSize = halfTapSquareSize;
    }

    public float getTapSquareSize () {
        return tapSquareSize;
    }

    public float getTouchDownX () {
        return touchDownX;
    }

    public float getTouchDownY () {
        return touchDownY;
    }

    public float getStageTouchDownX () {
        return stageTouchDownX;
    }

    public float getStageTouchDownY () {
        return stageTouchDownY;
    }

    /** Returns the amount on the x axis that the touch has been dragged since the last drag event. */
    public float getDeltaX () {
        return deltaX;
    }

    /** Returns the amount on the y axis that the touch has been dragged since the last drag event. */
    public float getDeltaY () {
        return deltaY;
    }

    public int getButton () {
        return button;
    }

    /** Sets the button to listen for, all other buttons are ignored. Default is {@link com.badlogic.gdx.Input.Buttons#LEFT}. Use -1 for any button. */
    public void setButton (int button) {
        this.button = button;
    }
}
