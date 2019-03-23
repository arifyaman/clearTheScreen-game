package com.xlipstudio.cleanthescreen;

import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.ThreeDTemp;
import com.xlipstudio.cleanthescreen.client.GameClient;
import com.xlipstudio.cleanthescreen.client.GameClientCallbacks;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.screen.ProfileScreen;
import com.xlipstudio.cleanthescreen.screen.Screen;
import com.xlipstudio.cleanthescreen.screen.ScreenHolder;

public class CleanTheScreenGame extends ThreeDTemp implements GameClientCallbacks {
    static GameClient gameClient;

    public static void setGameClient(GameClient gameClient) {
        CleanTheScreenGame.gameClient = gameClient;
    }

    public static ScreenHolder screenHolder;

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
        gameClient.register();
        changeScreen(ScreenHolder.getOpeningScreen());
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
    public AndroidUnit getThempAndroidUnit() {
        return super.getThempAndroidUnit();
    }

    @Override
    public void doInSplashScreenBackground() {
        ScreenHolder.init();
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
    public void wrapReceived(Wrap wrap) {
        ((Screen) screen).wrapReceived(wrap);
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
    public static void test() {
        System.out.println("asdas");
    }

}
