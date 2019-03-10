package com.xlip.threedtemp.Utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by arif on 04.10.2017.
 */

public class ColorLerp {
    private Color temp;
    private Color color1;
    private Color color2;
    private Lerp lr,lg,lb;




    public ColorLerp(Color color1, Color color2, float interpolation) {
        this.color1 = color1;
        this.color2 = color2;
        lr = new Lerp(color1.r , color2.r,interpolation);
        lg = new Lerp(color1.g , color2.g,interpolation);
        lb = new Lerp(color1.b , color2.b,interpolation);
        temp = new Color();
    }

    public Color getValue(float delta) {
        temp.set(lr.getValue(delta),lg.getValue(delta),lb.getValue(delta),1);
        return temp;
    }


}
