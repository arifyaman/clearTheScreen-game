package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.response.Response;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;

public class OpeningScreen extends Screen implements MyInputProcessor.MyInputCallback {
    private static OpeningScreen instance = new OpeningScreen();


    public static OpeningScreen getInstance() {
        return instance;
    }

    public OpeningScreen() {
        super();

    }

    @Override
    public void render(float delta) {
        super.render(delta);


    }

    @Override
    public void initialized() {
        MyInputProcessor processor = new MyInputProcessor(this);
        processor.setMyInputCallback(this);
        Gdx.input.setInputProcessor(processor);
        setClearColor(Color.WHITE);

    }

    @Override
    public boolean touchDown(Vector2 vector2, Vector2 vector21) {
        System.out.println(Settings.appheight);
        System.out.println(vector21);
        if(vector21.y >= Settings.appheight / 2){
            Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.GO, "PLAY"));
            CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
        }else {
            Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.GO, "PROFILE"));
            CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
        }

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
        if (wrap.getResponse().isResult()) {
            Response response = wrap.getResponse();
            if(response.getCode().equals("101")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getWaitingScreen());
            }else if(response.getCode().equals("102")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getProfileScreen());
            }
        }

    }

}
