package com.xlipstudio.cleanthescreen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.client.Client;

import java.io.IOException;

public class DesktopLauncher {
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
        new LwjglApplication(game, config);
        try {
            client = new Client(game, "a8ys791238hwdmf",  /*"51.38.126.60"*/   "localhost");
            client.start();
            CleanTheScreenGame.setGameClient(client);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Not able to connect");
            return;
        }
        //Client client = new Client(game, "a8ys791238hwdmf", "localhost");





    }
}
