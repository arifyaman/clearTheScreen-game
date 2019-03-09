package com.xlipstudio.cleanthescreen;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.client.Client;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 4;

		CleanTheScreenGame game = new CleanTheScreenGame();
		final Client client = new Client(game, "bchshY45923cz");

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				client.start();
			}
		});

		initialize(game, config);
	}
}
