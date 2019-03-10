package com.xlipstudio.cleanthescreen;

import android.os.AsyncTask;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xlipstudio.cleanthescreen.CleanTheScreenGame;
import com.xlipstudio.cleanthescreen.client.Client;
import com.xlipstudio.cleanthescreen.client.GameClient;

import java.util.concurrent.ExecutionException;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 4;

		final CleanTheScreenGame game = new CleanTheScreenGame();


		try {
			GameClient client = new StartGameClient().execute(game).get();
			CleanTheScreenGame.setGameClient(client);
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



		initialize(game, config);
	}


	class StartGameClient extends AsyncTask<CleanTheScreenGame, Void, GameClient> {

		private Exception exception;

		protected GameClient doInBackground(CleanTheScreenGame... games) {
			try {
				final Client client = new Client(games[0], "hzb6781724kdcVz23", "10.0.2.2");
				client.start();


				return client;
			} catch (Exception e) {
				this.exception = e;

				return null;
			}
		}

		protected void onPostExecute(GameClient feed) {
			// TODO: check this.exception
			// TODO: do something with the feed
		}
	}

}
