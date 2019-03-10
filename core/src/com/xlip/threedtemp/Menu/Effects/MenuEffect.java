package com.xlip.threedtemp.Menu.Effects;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Menu.Menu;

/**
 * Created by Arif on 23.07.2017.
 */

public class MenuEffect {

        public Matrix4 transformMatrix;
        public Vector3 viewScale;
        public Vector3 viewTranslation;

        protected Matrix4 orjTransformMatrix;
        protected Matrix4 tempMatrix;

        public float interpolation;
        public boolean started;


        public Menu.ScreenCallbacks screenCallbacks;
        public MenuEffectCallbacks menuEffectCallbacks;

        public MenuEffect() {
            this.tempMatrix = new Matrix4();

        }

        public MenuEffect(float interpolation) {
           this();
           this.interpolation = interpolation;
        }

        public void tick(float delta){
            transformMatrix.setTranslation(viewTranslation);

        }

        public void start() {
            started = true;
        }



        public void onFinished(){

        }

        public interface MenuEffectCallbacks{
            void finishParentMenu();

        }



//GETTER SETTER


    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Matrix4 getTransformMatrix() {
        return transformMatrix;
    }

    public void setTransformMatrix(Matrix4 transformMatrix) {
        this.transformMatrix = transformMatrix;
        orjTransformMatrix = new Matrix4(transformMatrix);
        setViewScale(transformMatrix.getScale(new Vector3()));
        setViewTranslation(transformMatrix.getTranslation(new Vector3()));
    }

    public float getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

    public Vector3 getViewScale() {
        return viewScale;
    }

    public void setViewScale(Vector3 viewScale) {
        this.viewScale = viewScale;
    }

    public Menu.ScreenCallbacks getScreenCallbacks() {
        return screenCallbacks;
    }

    public void setScreenCallbacks(Menu.ScreenCallbacks screenCallbacks) {
        this.screenCallbacks = screenCallbacks;
    }

    public void setViewTranslation(Vector3 viewTranslation) {
        this.viewTranslation = viewTranslation;
    }

    public MenuEffectCallbacks getMenuEffectCallbacks() {
        return menuEffectCallbacks;
    }

    public void setMenuEffectCallbacks(MenuEffectCallbacks menuEffectCallbacks) {
        this.menuEffectCallbacks = menuEffectCallbacks;
    }
}
