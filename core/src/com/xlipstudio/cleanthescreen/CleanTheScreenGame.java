package com.xlipstudio.cleanthescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.ThreeDTemp;
import com.xlipstudio.cleanthescreen.client.GameClient;
import com.xlipstudio.cleanthescreen.client.GameClientCallbacks;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.menu.NotConnectedMenu;
import com.xlipstudio.cleanthescreen.screen.OpeningScreen;
import com.xlipstudio.cleanthescreen.screen.Screen;

public class CleanTheScreenGame extends ThreeDTemp implements GameClientCallbacks {
    static GameClient gameClient;
    static AndroidUnit androidUnit;


    public static void setGameClient(GameClient gameClient) {
        CleanTheScreenGame.gameClient = gameClient;
    }

    public CleanTheScreenGame(AndroidUnit androidUnit) {
        super(androidUnit);
    }


    public CleanTheScreenGame() {
        super();

    }

    @Override
    public void create() {
        super.create();

        this.onSplashScreenFinished();
    }

    @Override
    public void onSplashScreenFinished() {
        super.onSplashScreenFinished();
        OpeningScreen openingScreen = new OpeningScreen();
        if(!gameClient.isConnected()) {
            openingScreen.setMenu(new NotConnectedMenu());
            changeScreen(openingScreen);
        }else {
            gameClient.register();
            changeScreen(openingScreen);
        }

    }


    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void doInSplashScreenBackground() {

    }

    @Override
    public void addModelTheseParts(ModelBuilder modelBuilder) {

    }

    public static GameClient getGameClient() {
        return gameClient;
    }


    public static void changeScreen(Screen screen) {
        screen.initialized();
        CleanTheScreenGame.screen = screen;

    }

    public static void changeScreen(Screen screen, Object object) {

        CleanTheScreenGame.screen = screen;
        screen.initialized(object);
    }

    @Override
    public void wrapReceived(final Wrap wrap) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                ((Screen) screen).wrapReceived(wrap);

            }
        });
    }

    @Override
    public void unableToConnect() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public static void exit(){
        Gdx.app.exit();
    }

    public static void test() {
        System.out.println("asdas");
    }


    public static AndroidUnit getAndroidUnit() {
        return androidUnit;
    }

    public static void setAndroidUnit(AndroidUnit androidUnit) {
        CleanTheScreenGame.androidUnit = androidUnit;
    }
}
