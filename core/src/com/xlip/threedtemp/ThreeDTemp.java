package com.xlip.threedtemp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlip.threedtemp.Objects.Model.DefaultModels;
import com.xlip.threedtemp.Screen.Screen;
import com.xlip.threedtemp.Screen.SplashScreen;
import com.xlip.threedtemp.Settings.Settings;

import java.io.File;


public abstract class ThreeDTemp extends ApplicationAdapter implements SplashScreen.MainCallBacks {
    private AndroidUnit androidUnit;
    public static Screen screen;
    private boolean paused;

    public ThreeDTemp(AndroidUnit androidUnit) {
        this.androidUnit = androidUnit;
        this.paused = false;
    }

    //for desktop mode
    public ThreeDTemp() {
        this(new AndroidUnit() {
            @Override
            public void share_score(File f) {

            }

            @Override
            public AchievementService getAchievementService() {
                return new AchievementService() {
                    @Override
                    public void triggerAchievements(String key, int times) {

                    }

                    @Override
                    public void showAchievements() {

                    }

                    @Override
                    public void opened() {

                    }

                    @Override
                    public void played() {

                    }
                };
            }

            @Override
            public LeaderBoardService getLeaderBoardService() {
                return new LeaderBoardService() {
                    @Override
                    public void display(String key) {

                    }

                    @Override
                    public void display() {

                    }

                    @Override
                    public void pushScore(String key, long score) {

                    }
                };
            }

            @Override
            public AdUnit getAdUnit() {
                return new AdUnit() {
                    @Override
                    public void showBottomBanner() {

                    }

                    @Override
                    public void showInterstitial() {

                    }

                    @Override
                    public void hideBottomBanner() {

                    }

                    @Override
                    public void showTopBanner() {

                    }
                };
            }

            @Override
            public AnalyticTracker getAnalyticTracker() {
                return new AnalyticTracker() {
                    @Override
                    public void setScreen(String screenName) {

                    }

                    @Override
                    public void sendEvent(String category, String action) {

                    }
                };
            }
        });
    }

    @Override
    public void create() {
        Assets.init();
        Settings.init();

        screen = new SplashScreen(2, this) {
            @Override
            public void doInBackGround() {
                DefaultModels defaultModels = new DefaultModels() {
                    @Override
                    public void addModelTheseParts(ModelBuilder modelBuilder) {
                        ThreeDTemp.this.addModelTheseParts(modelBuilder);
                    }
                };
                defaultModels.init();

                doInSplashScreenBackground();
            }
        };

        if(Settings.splashScreenEnabled) {
            onSplashScreenFinished();
        }

    }

    @Override
    public void onSplashScreenFinished() {


    }

    public abstract void doInSplashScreenBackground();

    public abstract void addModelTheseParts(ModelBuilder modelBuilder);


    @Override
    public void render() {
        final float delta = Math.min(1f / 30f, Gdx.graphics.getDeltaTime());
        if (!paused) {
            if (screen != null) {
                screen.render(delta);
            }
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {
        super.pause();
        this.paused = true;
    }

    @Override
    public void resume() {
        super.resume();
        this.paused = false;
    }

    public AndroidUnit getInnerAndroidUnit() {
        return androidUnit;
    }
}
