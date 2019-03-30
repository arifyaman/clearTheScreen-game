package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.response.Response;
import com.xlipstudio.cleanthescreen.menu.ProfileMenu;

import java.util.HashMap;

public class GameOverScreen extends Screen {
    private static GameOverScreen instance = new GameOverScreen();
    private BitmapFont font;
    private HashMap<String, String> result;

    public GameOverScreen() {
        super();
        font = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);
        font.setColor(Color.BLACK);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);


        this.myInputProcessor = new MyInputProcessor(this);
        setMenu(new ProfileMenu());

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        if (result != null) {
            int i = 1;
            for (String key : result.keySet()) {
                if(key.equals("result")) {
                    font.setColor(Assets.primaryColor);
                    font.getData().setScale(2);
                    font.draw(spriteBatch, result.get(key), -165, 400);
                    font.setColor(Color.BLACK);
                } else {
                    font.getData().setScale(1);
                    font.draw(spriteBatch, key, -300, 300 - (i * 100));
                    font.draw(spriteBatch, result.get(key), 100, 300 - (i * 100));
                }

                i++;
            }

        }

        spriteBatch.end();

    }

    @Override
    public void initialized() {

        Gdx.input.setInputProcessor(myInputProcessor);
        setClearColor(Color.WHITE);
    }

    @Override
    public void wrapReceived(Wrap wrap) {
        super.wrapReceived(wrap);
        if(wrap.getResponse().isResult()) {
            CleanTheScreenGame.changeScreen(new OpeningScreen());
            return;
        }

    }

    public HashMap<String, String> getResult() {
        return result;
    }

    public void setResult(HashMap<String, String> result) {
        this.result = result;
    }
}
