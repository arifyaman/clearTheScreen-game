package com.xlip.threedtemp.Menu.Effects.Defaults;

import com.badlogic.gdx.math.Matrix4;
import com.xlip.threedtemp.Utils.Lerp;
import com.xlip.threedtemp.Menu.Effects.MenuEffect;

/**
 * Created by Arif on 23.07.2017.
 */

public class DefaultMenuFinisher extends MenuEffect {
    Lerp fx,fy;
    private float xdif,ydif;
    private float endX, endY;

    public DefaultMenuFinisher(float interpolation) {
        super(interpolation);
        this.xdif = 0.0005f;
        this.ydif = 0.0002f;
        this.endX = 0;
        this.endY = 0;
    }


    @Override
    public void setTransformMatrix(Matrix4 transformMatrix) {
        super.setTransformMatrix(transformMatrix);
        float xdif = this.xdif;
        float ydif = this.ydif;


        fx = new Lerp(viewScale.x, viewScale.x + xdif, interpolation) {
            @Override
            public void onFinished() {
                DefaultMenuFinisher.this.onFinished();
                super.onFinished();
            }
        }.combineWith(endX,interpolation);

        fy = new Lerp(viewScale.y, viewScale.y + ydif, interpolation).combineWith(endY,interpolation);

    }


    @Override
    public void onFinished() {
        super.onFinished();
        if(menuEffectCallbacks != null) {
            menuEffectCallbacks.finishParentMenu();
        }



    }

    @Override
    public void tick(float delta) {
        transformMatrix.setToScaling(fx.getValue(delta),fy.getValue(delta),0);
        super.tick(delta);


    }

}
