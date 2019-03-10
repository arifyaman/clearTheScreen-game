package com.xlip.threedtemp.World;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;

/**
 * Created by Arif on 13.07.2017.
 */

public class Environment extends com.badlogic.gdx.graphics.g3d.Environment {
    public DirectionalLight dlight,dlight2,dlight3,dlight4;
    public PointLight pointLight;



    public Environment() {
        super();
        ColorAttribute ambient = new ColorAttribute(ColorAttribute.AmbientLight, 0.6f, 0.6f, 0.6f, 0.6f);
        dlight = new DirectionalLight().set(0.1f, 0.1f,0.1f, 0.05f,0.025f,1f);
        dlight2 = new DirectionalLight().set(0.8f, 0.8f, 0.8f, 0.6502156f,0.9053507f,0.24601352f);
        dlight3 = new DirectionalLight().set(0.8f, 0.8f, 0.8f, 0.6502156f,0.9053507f,-0.24601352f);

        dlight4 = new DirectionalLight().set(1, 1,1, -0.5917566f,0.347794f,0.3409844f);


       // set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        ColorAttribute fog = new ColorAttribute(ColorAttribute.Fog, Color.SKY);
        set(fog);
        // environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.9f, 0.9f, 0.9f, 1f));

        set(ambient);

        add(dlight);
        //add(dlight2);
        //add(dlight3);
        //add(dlight4);

    }
}
