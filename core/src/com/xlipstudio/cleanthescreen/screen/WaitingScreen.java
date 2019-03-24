package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.google.gson.Gson;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.game.GameConfig;
import com.xlipstudio.cleanthescreen.menu.WaitingPlayerMenu;

import java.util.Set;

public class WaitingScreen extends Screen {
    private static WaitingScreen instance = new WaitingScreen();
    protected Gson gson;


    public static WaitingScreen getInstance() {
        return instance;
    }

    public WaitingScreen() {
        super();

        gson = new Gson();
        this.myInputProcessor = new MyInputProcessor(this);
        setMenu(new WaitingPlayerMenu());

    }

    @Override
    public void render(float delta) {


        super.render(delta);
        Double r = Math.cos(iTime * 1.8);
        Double g = Math.cos(iTime * 1.61);
        Double b = Math.cos(iTime * 1.47);


        setClearColor(new Color(r.floatValue(),g.floatValue(),b.floatValue(),1f));
    }

    @Override
    public void initialized() {

        Gdx.input.setInputProcessor(myInputProcessor);
        setClearColor(Color.YELLOW);
    }


    @Override
    public void wrapReceived(final Wrap wrap) {
        super.wrapReceived(wrap);
        if (wrap.getResponse().isResult()) {
            Object payload = wrap.getResponse().getPayload();
            if (wrap.getResponse().getCode().equals("100")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getGameScreen(), this.gson.fromJson(((String) payload), GameConfig.class));
                finishLoading();
            } else if (wrap.getResponse().getCode().equals("101")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getOpeningScreen());
                finishLoading();
            }
        }
    }

}


