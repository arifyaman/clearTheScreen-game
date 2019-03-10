package com.xlip.threedtemp.Objects.Partials;


import com.badlogic.gdx.math.Vector3;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by Arif on 22.07.2017.
 */

public class Partial extends GameObject {
    Lerp fx,fy,fz;
    Vector3 targetPos;
    Vector3 orjPos;



    public Partial(Vector3 pos, float lerpStr) {
        super("plane");
        scaleMatrix(0.5f);
        float randomize = 1;
        orjPos = new Vector3(new Vector3(pos.x+random.nextFloat()*randomize, pos.y+random.nextFloat()*randomize,pos.z+random.nextFloat()*randomize ));
        setPosition(orjPos);
        fx = new Lerp(getPosition().x,getPosition().x,lerpStr){
            @Override
            public void onFinished() {
                onLerpFinished();
            }
        };

        fy = new Lerp(getPosition().y,getPosition().y,lerpStr);
        fz = new Lerp(getPosition().z,getPosition().z,lerpStr);

    }


    public void goTo(Vector3 pos) {
        this.targetPos = pos;
        fx.go(pos.x);
        fy.go(pos.y);
        fz.go(pos.z);
    }

    @Override
    public void update(float delta) {
        setPosition(fx.getValue(delta),fy.getValue(delta),fz.getValue(delta));

        super.update(delta);
    }

    public void onLerpFinished(){


    }


}
