package com.xlipstudio.cleanthescreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.ThreeDTemp;
import com.xlipstudio.cleanthescreen.client.GameClient;
import com.xlipstudio.cleanthescreen.communication.Wrap;
import com.xlipstudio.cleanthescreen.screen.GameScreen;
import com.xlipstudio.cleanthescreen.screen.OpeningScreen;
import com.xlipstudio.cleanthescreen.screen.Screen;
import com.xlipstudio.cleanthescreen.screen.WaitingScreen;

public class CleanTheScreenGame extends ThreeDTemp implements GameClient.GameClientCallbacks {
	static GameClient gameClient;

	public CleanTheScreenGame(AndroidUnit androidUnit) {
		super(androidUnit);
	}



	public CleanTheScreenGame() {
		super();
	}

	@Override
	public void create() {
		super.create();
		gameClient = new GameClient(this);
		gameClient.start();

		this.onSplashScreenFinished();
		gameClient.register();


	}

	@Override
	public void onSplashScreenFinished() {
		super.onSplashScreenFinished();
		screen = OpeningScreen.getInstance();
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

	}

	@Override
	public void addModelTheseParts(ModelBuilder modelBuilder) {

	}

	public static GameClient getGameClient() {
		return gameClient;
	}


	public static void changeScreen(Screen screen) {
		CleanTheScreenGame.screen = screen;
		screen.initialized();
	}

	@Override
	public void wrapReceived(Wrap wrap) {
		((Screen) screen).wrapReceived(wrap);
	}
}
