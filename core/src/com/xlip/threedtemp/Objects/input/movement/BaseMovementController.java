package com.xlip.threedtemp.Objects.input.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by arif on 06.01.2018.
 */

public abstract class BaseMovementController {
    public Vector3 pos;
    private Vector3 next;
    private boolean finished;



    public BaseMovementController(Vector3 position) {
        this.pos = position;
        next = new Vector3();
        finished = true;
    }


    public abstract boolean touchDown(Vector2 cxy);

    public abstract boolean touchUp(Vector2 cxy);

    public abstract boolean touchDragged(Vector2 cxy);

    public void process(Vector3 newPosition) {
        pos = newPosition;
    }

    public void go(Vector3 pos) {
        go(pos.x,pos.y,pos.z);
    }
    public void go(float x, float y, float z) {

        setNext(x,y,z);
    }

    /**
     *
     * @param delta
     * @return returns processed position.
     */
    public Vector3 update(float delta) {
        if(pos.x == next.x && pos.y == next.y && pos.z==next.z && !finished){
            finished = true;
            onFinished();
        }

        return pos;
    }

    public void onFinished(){

    }

    protected void setNext(Vector3 next) {
        setNext(next.x,next.y,next.z);
    }

    protected void setNext(float x, float y,float z) {
        this.next.set(x,y,z);
        finished=false;
    }


}
