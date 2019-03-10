package com.xlip.threedtemp.Utils;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Arif on 14.07.2017.
 */

public class Sindeg {
    private float start;
    private float end;
    private float strenght;

    private float angle;

    private boolean finished;
    private Sindeg combined;


    public Sindeg(float start, float end, float strenght) {
        this.start = start;
        this.end = end;
        this.angle = 0;

        this.strenght = strenght;
        finished = false;
    }



    public float getValue(float delta) {
        if(!finished) {
            if (angle >= 90) {
                finished = true;
                return end;
            }

            float degVal = MathUtils.sinDeg(angle);

            angle += strenght*delta;


            if (start < end) {
                return start + degVal * (end - start);
            } else {
                return start - degVal * (start - end);
            }

        } else {
            if(combined != null) {
                return combined.getValue(delta);
            }

            return end;
        }

    }

    public Sindeg combineWith(float end, float strenght){
        if(combined == null) {
            combined = new Sindeg(this.end,end,strenght);
        } else {
            combined.combineWith(end,strenght);
        }
        return this;
    }


}
