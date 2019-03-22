package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.Gson;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.request.Request;
import com.xlipstudio.cleanthescreen.communication.request.RequestType;
import com.xlipstudio.cleanthescreen.communication.sub.WrapType;
import com.xlipstudio.cleanthescreen.game.GameConfig;

public class WaitingScreen extends Screen implements MyInputProcessor.MyInputCallback {
    private static WaitingScreen instance = new WaitingScreen();
    protected Gson gson;

    public static WaitingScreen getInstance() {
        return instance;
    }

    public WaitingScreen() {
        super();
        gson = new Gson();

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
        setClearColor(Color.YELLOW);
    }

    @Override
    public boolean touchDown(Vector2 vector2, Vector2 vector21) {
        Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.EXIT, null));
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
    public void wrapReceived(final Wrap wrap) {
        super.wrapReceived(wrap);
        if(wrap.getResponse().isResult()) {
            Object payload = wrap.getResponse().getPayload();
            if(wrap.getResponse().getCode().equals("100")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getGameScreen(),this.gson.fromJson(((String) payload), GameConfig.class));
            }else if(wrap.getResponse().getCode().equals("101")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getOpeningScreen());
            }
        }
    }

}


