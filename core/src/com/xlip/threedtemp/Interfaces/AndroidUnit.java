package com.xlip.threedtemp.Interfaces;

import java.io.File;

/**
 * Created by Yaman on 20.02.2016.
 */
public interface AndroidUnit {

    void share_score(File f);

    AchievementService getAchievementService();
    LeaderBoardService getLeaderBoardService();
    AdUnit getAdUnit();
    AnalyticTracker getAnalyticTracker();


    interface AchievementService {
        void triggerAchievements(String key, int times);
        void showAchievements();
        void opened();
        void played();
    }

    interface LeaderBoardService {
        void display(String key);
        void display();
        void pushScore(String key, long score);
    }

    interface AdUnit {
        void showBottomBanner();
        void showTopBanner();
        void showInterstitial();
        void hideBottomBanner();
    }

    interface AnalyticTracker{
        void setScreen(String screenName);
        void sendEvent(String category, String action);

    }



}
