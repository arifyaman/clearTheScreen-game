package com.xlipstudio.cleanthescreen;

import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.ThreeDTemp;
import com.xlipstudio.cleanthescreen.client.GameClient;
import com.xlipstudio.cleanthescreen.screen.GameScreen;

public class CleanTheScreenGame extends ThreeDTemp {
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
		onSplashScreenFinished();
		gameClient = new GameClient();

	}

	@Override
	public void onSplashScreenFinished() {
		super.onSplashScreenFinished();


		screen = GameScreen.getInstance();
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
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
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
}
