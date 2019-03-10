package com.xlip.threedtemp.Shader;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.xlip.threedtemp.Utils.Lerp;

/**
 * Created by Arif on 6.08.2017.
 */

public class MyShaderProgram extends ShaderProgram {
    private float time;
    private Lerp lerp;


    public MyShaderProgram(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
        lerp = new Lerp(0,1,1f);
        time = 0;
        if (isCompiled() == false) throw new IllegalArgumentException("Error compiling shader: " + getLog());

    }

    public void update(float delta) {
        time = lerp.getValue(delta);

    }

    @Override
    public void begin() {
        super.begin();

        setUniformf("u_globalTime",time);
    }

    @Override
    public void end() {
        super.end();
    }

    public void setTimeManually(float time){
        lerp = new Lerp(0,time,1f);
        this.time = time;
    }
}
