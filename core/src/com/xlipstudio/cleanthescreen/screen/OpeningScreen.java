package com.xlipstudio.cleanthescreen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.xlip.threedtemp.Input.MyInputProcessor;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.communication.response.Response;
import com.xlipstudio.cleanthescreen.menu.OpeningMenu;

public class OpeningScreen extends Screen {
    private static OpeningScreen instance = new OpeningScreen();


    public static OpeningScreen getInstance() {
        return instance;
    }

    private OpeningMenu menu;

    public OpeningScreen() {
        super();
        this.myInputProcessor = new MyInputProcessor(this);
        menu = new OpeningMenu();
        setMenu(menu);
    }

    @Override
    public void render(float delta) {
        super.render(delta);


    }

    @Override
    public void initialized() {
        Gdx.input.setInputProcessor(myInputProcessor);
        setClearColor(Color.WHITE);
    }

   /* @Override
    public boolean touchDown(Vector2 vector2, Vector2 vector21) {
        System.out.println(Settings.appheight);
        System.out.println(vector21);
        *//*if(vector21.y >= Settings.appheight / 2){
            Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.GO, "PLAY"));
            CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
        }else {
            Wrap wrap = new Wrap(WrapType.REQUEST, new Request(RequestType.GO, "PROFILE"));
            CleanTheScreenGame.getGameClient().dispatchWrap(wrap);
        }*//*

        return false;
    }

    @Override
    public boolean touchUp(Vector2 vector2, Vector2 vector21) {

        return false;
    }

    @Override
    public boolean touchDragged(Vector2 vector2, Vector2 vector21) {
        return false;
    }*/

    @Override
    public void wrapReceived(Wrap wrap) {
        super.wrapReceived(wrap);
        if (wrap.getResponse().isResult()) {
            Response response = wrap.getResponse();
            if (response.getCode().equals("101")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getWaitingScreen());
                finishLoading();
            } else if (response.getCode().equals("102")) {
                CleanTheScreenGame.changeScreen(ScreenHolder.getProfileScreen());
                finishLoading();
            }
        }

    }

}
