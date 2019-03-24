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

public class ProfileScreen extends Screen {
    private static ProfileScreen instance = new ProfileScreen();
    private BitmapFont font;
    private HashMap<String, String> profileDetails;

    public ProfileScreen() {
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
        if (profileDetails != null) {
            int i = 1;
            for (String key : profileDetails.keySet()) {
                font.draw(spriteBatch, key, -300, 300 - (i * 100));
                font.draw(spriteBatch, profileDetails.get(key), 100, 300 - (i * 100));
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
        Response response = wrap.getResponse();
        System.out.println(response.getCode());
        if (response.getCode().equals("10")) {
            this.profileDetails = ((HashMap) response.getPayload());
            System.out.println(this.profileDetails);
            finishLoading();
        } else if (response.getCode().equals("1")) {
            CleanTheScreenGame.changeScreen(ScreenHolder.getOpeningScreen());
            finishLoading();
        }

    }

}
