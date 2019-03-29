package com.xlipstudio.cleanthescreen.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.client.Client;

public class DesktopLauncher2 {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Settings.appwidth;
        config.height = Settings.appheight;
        config.x = Settings.x;
        config.y = Settings.y;
        config.samples = 3;

        final CleanTheScreenGame game = new CleanTheScreenGame();
        new LwjglApplication(game, config);

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {

                Client client = new Client(game, "v8ys791238hwdmf", "localhost");
                client.start();
                CleanTheScreenGame.setGameClient(client);


            }
        });


        //Client client = new Client(game, "a8ys791238hwdmf", "localhost");


    }
}
