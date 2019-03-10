package com.xlip.threedtemp.Input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Menu.Menu;
import com.xlip.threedtemp.Screen.Screen;
import com.xlip.threedtemp.Settings.Settings;

/**
 * Created by Arif on 15.07.2017.
 */

public class MyInputProcessor implements InputProcessor {
    OrthographicCamera camera;
    Menu menu;
    Vector2 temp2;
    Vector3 temp3;
    private MyInputCallback myInputCallback;

    public MyInputProcessor(Screen screen) {
        this.camera = screen.orthographicCamera;
        this.temp2 = new Vector2();
        this.temp3 = new Vector3();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 converted = new Vector2(converted(screenX,screenY));
        temp2.set(screenX,screenY);
        if(menu != null)
            menu.touchDown(converted, temp2);
        if(myInputCallback != null)
            myInputCallback.touchDown(converted,temp2);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 converted = new Vector2(converted(screenX,screenY));
        temp2.set(screenX,screenY);
        if(menu != null)
            menu.touchUp(converted, temp2);
        if(myInputCallback != null)
            myInputCallback.touchUp(converted, temp2);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 converted = new Vector2(converted(screenX,screenY));
        temp2.set(screenX,screenY);
        if(menu != null)
            menu.touchDragged(converted, temp2);
        if(myInputCallback != null)
            myInputCallback.touchDragged(converted,temp2);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector2 converted = new Vector2(converted(screenX,screenY));

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Vector2 converted(float x, float y) {
        temp3.set(x,y,0);
        camera.unproject(temp3);
        return temp2.set(temp3.x,temp3.y);
    }


    public interface MyInputCallback {
        boolean touchDown(Vector2 wCoords, Vector2 sCoors);
        boolean touchUp(Vector2 wCoords, Vector2 sCoors);
        boolean touchDragged(Vector2 wCoords, Vector2 sCoors);
    }

    public InputProcessor setMyInputCallback(MyInputCallback myInputCallback) {
        this.myInputCallback = myInputCallback;
        return this;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
