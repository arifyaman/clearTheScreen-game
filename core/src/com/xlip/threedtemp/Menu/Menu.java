package com.xlip.threedtemp.Menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Menu.Effects.MenuEffect;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Utils.MenuUtils.ObjectPlacer;
import com.xlip.threedtemp.World.World;

/**
 * Created by Arif on 14.07.2017.
 */

public class Menu implements MenuObject.MenuObjectCallBacks, MyInputProcessor.MyInputCallback, MenuEffect.MenuEffectCallbacks {
    protected SpriteBatch spriteBatch;
    protected Array<MenuObject> menuObjects;
    protected Matrix4 view;
    protected ScreenCallbacks screenCallbacks;

    protected MenuEffect menuFinisher;
    protected MenuEffect menuOpener;
    private boolean finished;

    private ObjectPlacer objectPlacer;



   public Menu() {
        this.finished = false;
        this.spriteBatch = new SpriteBatch();
        spriteBatch.enableBlending();
        this.menuObjects = new Array<MenuObject>();
    }

    public void setView(Matrix4 projectionView) {
        this.view = projectionView;
        if(menuFinisher != null) {
            menuFinisher.setTransformMatrix(spriteBatch.getTransformMatrix());
            menuFinisher.setMenuEffectCallbacks(this);
        }

        if(menuOpener != null) {
            menuOpener.setTransformMatrix(spriteBatch.getTransformMatrix());
            menuFinisher.setMenuEffectCallbacks(this);
        }
    }


    public void addMenuObject(MenuObject menuObject) {
        menuObject.setMenuObjectCallBacks(this);
        this.menuObjects.add(menuObject);
    }

    public void update(float delta) {
        if(objectPlacer != null){
            objectPlacer.update(delta);
        }
        if(menuOpener != null && menuOpener.isStarted()){
            menuOpener.tick(delta);
        }

        if(menuFinisher != null &&  menuFinisher.isStarted()){
            menuFinisher.tick(delta);
        }

        for (MenuObject m :
                menuObjects) {
            m.update(delta);
        }
    }

    public void draw(float delta) {

        if(menuObjects.size > 0) {

            spriteBatch.setProjectionMatrix(view);
            spriteBatch.begin();
            for(MenuObject object : menuObjects) {
               object.draw(spriteBatch,delta);
            }
            spriteBatch.end();



        }
    }

    @Override
    public void finishParentMenu() {
        this.finished = true;
    }

    @Override
    public boolean touchDown(Vector2 wCoords, Vector2 sCoors) {
        for(MenuObject object : menuObjects) {
            object.touchDown(wCoords,sCoors);
        }
        if(objectPlacer != null){
            objectPlacer.touchDown(wCoords, sCoors);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 wCoords, Vector2 sCoors) {
        for(MenuObject object : menuObjects) {
            object.touchUp(wCoords,sCoors);
        }
        if(objectPlacer != null){
            objectPlacer.touchUp(wCoords, sCoors);
        }
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 wCoords, Vector2 sCoors) {
        for(MenuObject object : menuObjects) {
            object.touchDragged(wCoords,sCoors);
        }
        if(objectPlacer != null){
            objectPlacer.touchDragged(wCoords, sCoors);
        }
        return false;
    }


    @Override
    public boolean finish() {
        if(menuFinisher == null)
            this.finished = true;
        else
            menuFinisher.start();

        return false;
    }




    public interface ScreenCallbacks {
        void setMenu(Menu menu);
        World getWorld();

    }




    //GETTER SETTER


    public Matrix4 getView() {
        return view;
    }



    public ScreenCallbacks getScreenCallbacks() {
        return screenCallbacks;
    }

    public void setScreenCallbacks(ScreenCallbacks screenCallbacks) {
        this.screenCallbacks = screenCallbacks;
    }

    public MenuEffect getMenuFinisher() {
        return menuFinisher;
    }

    public void setMenuFinisher(MenuEffect menuFinisher) {
        this.menuFinisher = menuFinisher;
    }

    public MenuEffect getMenuOpener() {
        return menuOpener;
    }

    public void setMenuOpener(MenuEffect menuOpener) {
        this.menuOpener = menuOpener;
        this.menuOpener.start();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatchTransformMatrix(Matrix4 transformMatrix) {
        spriteBatch.setTransformMatrix(transformMatrix);
    }

    public boolean isFinished() {
        return finished;
    }

    public ObjectPlacer getObjectPlacer() {
        return objectPlacer;
    }

    public void setObjectPlacer(ObjectPlacer objectPlacer) {
        this.objectPlacer = objectPlacer;
    }
}
