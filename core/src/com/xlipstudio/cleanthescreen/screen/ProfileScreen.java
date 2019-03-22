package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Assets;
import com.xlip.threedtemp.Input.Abs.Clickable;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.response.Response;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;

import java.util.HashMap;

public class ProfileScreen extends Screen implements MyInputProcessor.MyInputCallback {
    private static ProfileScreen instance = new ProfileScreen();
    private BitmapFont font;
    private HashMap<String, String> profileDetails;

    public ProfileScreen() {
        super();
        font = new BitmapFont(Gdx.files.internal("obelixfnt.fnt"), new TextureRegion(Assets.fontTexture), false);
        font.setColor(Color.BLACK);
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        if(profileDetails != null){
            int i = 1;
            for (String key: profileDetails.keySet()){
                font.draw(spriteBatch, key, -300, 300 - (i*100));
                font.draw(spriteBatch, profileDetails.get(key), 100, 300 - (i*100));
                i++;
            }

        }

        spriteBatch.end();

    }

    @Override
    public void initialized() {
        MyInputProcessor processor = new MyInputProcessor(this);
        processor.setMyInputCallback(this);
        Gdx.input.setInputProcessor(processor);

    }

    @Override
    public boolean touchDown(Vector2 vector2, Vector2 vector21) {
        Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.EXIT,null));
        CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 vector2, Vector2 vector21) {

        return false;
    }

    @Override
    public boolean touchDragged(Vector2 vector2, Vector2 vector21) {
        return false;
    }

    @Override
    public void wrapReceived(Wrap wrap) {
        super.wrapReceived(wrap);
        Response response= wrap.getResponse();
        System.out.println(response.getCode());
        if(response.getCode().equals("10")) {
          this.profileDetails = ((HashMap) response.getPayload());
            System.out.println(this.profileDetails);
        }else if(response.getCode().equals("1")) {
            CleanTheScreenGame.changeScreen(ScreenHolder.getOpeningScreen());
        }

    }

}
