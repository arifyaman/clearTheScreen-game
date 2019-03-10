package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by arif on 06.01.2018.
 */

public class DragMovementController extends BaseMovementController {
    protected float dragDeltaX = 0.01f;
    protected float dragDeltaY = 0.01f;

    protected boolean reversedX;
    protected boolean reversedY;
    protected boolean reversedZ;
    protected boolean isTocuhed;


    protected boolean disabledX;
    protected boolean disabledY;
    protected boolean disabledZ;


    protected float moveX, moveY, moveZ;


    public Vector2 touched;


    public DragMovementController(Vector3 position) {
        super(position);
        this.touched = new Vector2();
        isTocuhed = false;
    }

    @Override
    public boolean touchDown(Vector2 cxy) {
        isTocuhed = true;
        touched.set(cxy);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        isTocuhed = false;
        touched.set(cxy);
        return false;
    }

    public void dragged(Vector2 cxy) {
        float diffX = touched.x - cxy.x;
        moveX = diffX * dragDeltaX * (reversedX ? -1 : 1);

        float diffZ = touched.y - cxy.y;

        if (!disabledZ) {
            moveZ = diffZ * dragDeltaY * (reversedY ? -1 : 1);
        }
        touched.set(cxy);
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {

        return false;
    }

    public DragMovementController setReversedX() {
        this.reversedX = true;
        return this;
    }

    public DragMovementController setReversedY() {
        this.reversedY = true;
        return this;
    }

    public DragMovementController setReversedZ() {
        this.reversedY = true;
        return this;
    }

    public float getDragDeltaX() {
        return dragDeltaX;
    }

    public void setDragDeltaX(float dragDeltaX) {
        this.dragDeltaX = dragDeltaX;
    }

    public float getDragDeltaY() {
        return dragDeltaY;
    }

    public void setDragDeltaY(float dragDeltaY) {
        this.dragDeltaY = dragDeltaY;
    }

    public boolean isDisabledX() {
        return disabledX;
    }

    public void setDisabledX(boolean disabledX) {
        this.disabledX = disabledX;
    }

    public boolean isDisabledY() {
        return disabledY;
    }

    public void setDisabledY(boolean disabledY) {
        this.disabledY = disabledY;
    }

    public boolean isDisabledZ() {
        return disabledZ;
    }

    public void setDisabledZ(boolean disabledZ) {
        this.disabledZ = disabledZ;
    }

    public float getMoveX() {
        return moveX;
    }

    public float getMoveY() {
        return moveY;
    }

    public float getMoveZ() {
        return moveZ;
    }
}

