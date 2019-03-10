package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by arif on 03.02.2018.
 */

public class InstantDragMovementController extends DragMovementController {

    public InstantDragMovementController(Vector3 position) {
        super(position);
    }

    @Override
    public boolean touchDown(Vector2 cxy) {
        return super.touchDown(cxy);
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        return super.touchUp(cxy);
    }

    @Override
    public void dragged(Vector2 cxy) {
        float diffX = touched.x - cxy.x;
        moveX = diffX * dragDeltaX * (reversedX ? -1 : 1);


        float diffY = touched.y - cxy.y;
        moveY = diffY * dragDeltaY * (reversedY ? -1 : 1);
        touched.set(cxy);
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
        dragged(cxy);
        process(pos.add(moveX, moveY, pos.z));
        return false;

    }
}
