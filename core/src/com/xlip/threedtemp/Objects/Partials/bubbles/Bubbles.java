package com.xlip.threedtemp.Objects.Partials.bubbles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.xlip.threedtemp.Objects.GameObject;
import com.xlip.threedtemp.Objects.Partials.Partial;

import java.util.Random;


/**
 * Created by Arif on 22.07.2017.
 */

public class Bubbles extends Array<GameObject> {





    public Bubbles(int size, Vector3 pos, float lerpStrenght, TextureRegion textureRegion, float randomizeSpread, Color color) {

        for (int i = 0; i < size; i++) {
            Partial next = ((Partial) new Partial(pos, lerpStrenght) {
                float dissappearRandomize;
                @Override
                public void onCreate() {
                    super.onCreate();
                    dissappearRandomize = 3f + 1*random.nextFloat();
                }

                @Override
                public void update(float delta) {
                    float desc = (0.8f + dissappearRandomize*delta ) > 1 ? 0.9f * delta : 0.8f + dissappearRandomize*delta;
                    scaleMatrix(desc);
                    super.update(delta);
                }

                @Override
                public void onLerpFinished() {
                    dispose();
                }
            }.setTexture(textureRegion));

            if(color == null){
                Random random = new Random();
                next.setColor(new Color(random.nextFloat(),random.nextFloat(),random.nextFloat(),1f));
            } else {
                next.setColor(color);
            }


            Vector3 spread = new Vector3( pos.x + next.random.nextFloat()*randomizeSpread * (next.random.nextBoolean() ? 1 : -1) , pos.y+next.random.nextFloat()*randomizeSpread*(next.random.nextBoolean() ? 1 : -1), pos.z + next.random.nextFloat()*randomizeSpread* (next.random.nextBoolean() ? 1 : -1));

            next.goTo(spread);
            add(next);
        }
    }

}
