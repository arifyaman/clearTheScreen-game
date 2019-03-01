package com.xlipstudio.cleanthescreen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xlip.threedtemp.Settings.Settings;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Settings.appwidth;
		config.height = Settings.appheight;
		config.x = Settings.x;
		config.y = Settings.y;
		config.samples = 3;

		new LwjglApplication(new CleanTheScreenGame(), config);
	}
}
