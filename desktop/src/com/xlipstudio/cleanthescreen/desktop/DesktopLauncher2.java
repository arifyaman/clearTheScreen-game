package com.xlipstudio.cleanthescreen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.client.Client;

import java.io.IOException;

public class DesktopLauncher2 {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Settings.appwidth;
        config.height = Settings.appheight;
        config.x = Settings.x;
        config.y = Settings.y;
        config.samples = 3;


        String host = System.getProperty("gamehost");

        CleanTheScreenGame game = new CleanTheScreenGame();
        Client client = null;
        try {
            client = new Client(game, "v8ys791238hwdmf", "localhost");
            client.start();
            CleanTheScreenGame.setGameClient(client);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Not able to connect");
            return;
        }
        //Client client = new Client(game, "a8ys791238hwdmf", "localhost");


        new LwjglApplication(game, config);

    }
}
