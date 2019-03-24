package com.xlipstudio.cleanthescreen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Menu.Object.MenuObject;
import com.xlip.threedtemp.Settings.Settings;

public class Menu extends com.xlip.threedtemp.Menu.Menu {
    protected boolean loading;
    protected MenuObject loadingObject;

    public Menu() {
        super();
        BitmapFont font = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);
        font.setColor(Color.BLACK);
        font.getData().setScale(1.2f);

        loadingObject = new MenuObject(Assets.loading, new Vector2(-Settings.orto_width / 2, -Settings.orto_height / 2), new Vector2(Settings.orto_width, Settings.orto_height)) {
            float iTime = 0;
            @Override
            public void draw(SpriteBatch batch, float delta) {
                super.draw(batch, delta);
                iTime+=delta;
                if(iTime <= 0.4f && iTime < 0.4f){
                    this.title = "Loading...";
                }else if(iTime >= 0.4f && iTime < 0.8f){
                    this.title = "Loading";
                }else if(iTime >= 0.8f && iTime < 1.2f){
                    this.title = "Loading.";
                }else if(iTime > 1.2f && iTime < 1.6f){
                    this.title = "Loading..";
                }else {
                    iTime = 0;
                }
            }
        };
        loadingObject.setBitmapFont(font);
        loadingObject.setTitle("Loading...",-Settings.orto_height / 2 + 50,Settings.orto_width / 2 - 100);



    }

    @Override
    public void draw(float delta) {

        super.draw(delta);
        spriteBatch.begin();
        if (loading) {
            loadingObject.draw(spriteBatch,delta);
        }
        spriteBatch.end();

    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }


    @Override
    public boolean touchDown(Vector2 wCoords, Vector2 sCoors) {
        if(loading)return false;
        return super.touchDown(wCoords, sCoors);
    }

    @Override
    public boolean touchUp(Vector2 wCoords, Vector2 sCoors) {
        if(loading)return false;
        return super.touchUp(wCoords, sCoors);
    }

    @Override
    public boolean touchDragged(Vector2 wCoords, Vector2 sCoors) {
        if(loading)return false;
        return super.touchDragged(wCoords, sCoors);
    }
}
