package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by arif on 06.01.2018.
 */

public class LerpMovementController extends DragMovementController {
    private Lerp lx,ly,lz;

    public LerpMovementController(Vector3 position, float interplationX, float interplationY, float interplationZ) {
        super(position);
        lx = new Lerp(pos.x,pos.x,interplationX);
        ly = new Lerp(pos.y,pos.y,interplationY);
        lz = new Lerp(pos.z,pos.z,interplationZ);
    }

    public LerpMovementController(Vector3 position) {
        this(position,11,11,11);

    }

    public void resetLerps() {
        lx = new Lerp(pos.x,pos.x,lx.getInterpolation());
        ly = new Lerp(pos.y,pos.y,ly.getInterpolation());
        lz = new Lerp(pos.z,pos.z,lz.getInterpolation());
    }

    @Override
    public boolean touchDown(Vector2 cxy) {
        super.touchDown(cxy);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 cxy) {
        super.touchUp(cxy);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 cxy) {
        super.dragged(cxy);
        lx.addToEnd(moveX);
        ly.addToEnd(moveZ);
        lz.addToEnd(moveY);
        setNext(lx.getEnd(),ly.getEnd(),lz.getEnd());
        return false;
    }

    @Override
    public void go(float x, float y, float z ) {
        super.go(x,y,z);
        lx.go(x);
        ly.go(y);
        lz.go(z);
    }

    @Override
    public Vector3 update(float delta) {
        pos.x = lx.getValue(delta);
        pos.y = ly.getValue(delta);
        pos.z = lz.getValue(delta);
        return super.update(delta);
    }
}
