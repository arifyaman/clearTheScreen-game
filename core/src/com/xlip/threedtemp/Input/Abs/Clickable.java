package com.xlip.threedtemp.Input.Abs;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Input.MyInputProcessor;

/**
 * Created by Arif on 15.07.2017.
 */

public abstract class Clickable implements MyInputProcessor.MyInputCallback {
    private Rectangle wh;
    private boolean onIt = false;
    private boolean disabled = false;
    private boolean preClick = false;

    public Clickable(Vector2 wh, Vector2 position) {
        this.wh = new Rectangle(position.x,position.y,wh.x,wh.y);
    }

    @Override
    public boolean touchDown(Vector2 wCoords, Vector2 sCoors) {
        if(wh.contains(wCoords) && !disabled) {
            onIt = true;
            preClick = true;
        } else {
            preClick = false;
            onIt = false;
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 wCoords, Vector2 sCoors) {
        if(wh.contains(wCoords) && preClick){
            click();
        }
        preClick = false;
        onIt = false;
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 wCoords, Vector2 sCoors) {
        onIt = wh.contains(wCoords) && preClick;
        return false;
    }


    private void click() {

        clicked();
    }


    public abstract void clicked();

    public interface OnClickListener{
        void onClick();

    }

    //GETTER SETTER


    public Rectangle getWh() {
        return wh;
    }

    public boolean isOnIt() {
        return onIt;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setWh(float width,float height){
        this.wh.setWidth(width);
        this.wh.setHeight(height);
    }
}

