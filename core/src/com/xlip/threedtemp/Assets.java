package com.xlip.threedtemp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Arif on 15.07.2017.
 */

public class Assets {

    public static String blurSahderFragment;
    public static String testFragment;
    public static String barrelBlurFragment;
    public static String defaultShaderVertex;

    public static Texture splash;
    public static Texture fontTexture;


    //endregion
    
    
    public static void init() {

        splash = new Texture("splash.png");
        splash.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        blurSahderFragment = Gdx.files.internal("blur.frag.glsl").readString();
        barrelBlurFragment = Gdx.files.internal("barrelBlur.glsl").readString();
        testFragment = Gdx.files.internal("testFragment.glsl").readString();
        defaultShaderVertex = Gdx.files.internal("default.vertex.glsl").readString();
        fontTexture = new Texture(Gdx.files.internal("obelixfnt.png"));
        fontTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image



    }




}
