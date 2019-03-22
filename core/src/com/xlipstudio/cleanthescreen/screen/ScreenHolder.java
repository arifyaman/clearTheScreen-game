package com.xlipstudio.cleanthescreen.screen;

public class ScreenHolder {
    public static WaitingScreen waitingScreen;
    public static OpeningScreen openingScreen;
    public static GameScreen  gameScreen;
    public static MainScreen  mainScreen;
    public static ProfileScreen  profileScreen;

    public static void init() {
        waitingScreen = new WaitingScreen();
        openingScreen = new OpeningScreen();
        gameScreen = new GameScreen();
        mainScreen = new MainScreen();
        profileScreen = new ProfileScreen();
    }

    public static WaitingScreen getWaitingScreen() {
        return waitingScreen;
    }

    public static OpeningScreen getOpeningScreen() {
        return openingScreen;
    }

    public static GameScreen getGameScreen() {
        return gameScreen;
    }

    public static MainScreen getMainScreen() {
        return mainScreen;
    }

    public static ProfileScreen getProfileScreen() {
        return profileScreen;
    }
}
