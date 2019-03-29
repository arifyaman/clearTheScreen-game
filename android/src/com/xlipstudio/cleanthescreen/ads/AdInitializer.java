package com.xlipstudio.cleanthescreen.ads;

import android.app.Activity;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.*;
import com.xlip.threedtemp.Interfaces.AndroidUnit;
import com.xlipstudio.cleanthescreen.R;

/**
 * Created by Arif on 5.08.2017.
 */

public class AdInitializer implements AndroidUnit.AdUnit {
    private Activity activity;

    private String bottom_banner_id;
    private String interstitial_id;

    private AdView top_banner;
    private AdView bottom_banner;
    private InterstitialAd interstitialAd;

    public AdInitializer(Activity activity) {
        this.activity = activity;
        this.bottom_banner_id = activity.getResources().getString(R.string.bottom_banner_id);
        this.interstitial_id = activity.getResources().getString(R.string.interstitial_id);
    }


    public View getView(View gameView) {

        setUpAds();

        ConstraintLayout constraintLayout = new ConstraintLayout(activity);

        ConstraintLayout.LayoutParams topBannerParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        topBannerParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        topBannerParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        topBannerParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        top_banner.setLayoutParams(topBannerParams);


        ConstraintLayout.LayoutParams bottomBannerParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomBannerParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        bottomBannerParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        bottomBannerParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        bottomBannerParams.horizontalBias = 0f;
        bottom_banner.setLayoutParams(bottomBannerParams);


        ConstraintLayout.LayoutParams gameParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
        gameParams.topToBottom = R.id.top_banner_id;
        gameParams.bottomToTop = R.id.bottom_banner_id;
        gameParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        gameParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        gameView.setLayoutParams(gameParams);

        constraintLayout.addView(top_banner);
        constraintLayout.addView(bottom_banner);
        constraintLayout.addView(gameView);


        return constraintLayout;
    }

    private void setUpAds() {
        //top_banner = new AdView(activity);

        top_banner = new AdView(activity);
        top_banner.setAdSize(AdSize.SMART_BANNER);
        top_banner.setBackgroundColor(Color.TRANSPARENT);
        top_banner.setAdUnitId(activity.getResources().getString(R.string.top_banner_id));
        top_banner.setId(R.id.top_banner_id);


        top_banner.setBackgroundColor(Color.TRANSPARENT);
        top_banner.setVisibility(View.INVISIBLE);
        AdRequest.Builder topBannerBuilder = new AdRequest.Builder();
        topBannerBuilder.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
        AdRequest request1 = topBannerBuilder.build();
        top_banner.loadAd(request1);


        bottom_banner = new AdView(activity);
        bottom_banner.setAdSize(AdSize.SMART_BANNER);
        bottom_banner.setBackgroundColor(Color.TRANSPARENT);
        bottom_banner.setAdUnitId(activity.getResources().getString(R.string.bottom_banner_id));
        bottom_banner.setId(R.id.bottom_banner_id);

        bottom_banner.setBackgroundColor(Color.TRANSPARENT);
        bottom_banner.setVisibility(View.INVISIBLE);
        AdRequest.Builder bottomBannerBuilder = new AdRequest.Builder();
        bottomBannerBuilder.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
        AdRequest bottomRequest = bottomBannerBuilder.build();
        bottom_banner.loadAd(bottomRequest);


        interstitialAd = new InterstitialAd(activity);
        interstitialAd.setAdUnitId(interstitial_id);
        AdRequest.Builder builder2 = new AdRequest.Builder();
        builder2.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
        AdRequest request3 = builder2.build();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadInterstitialAd();
            }
        });

        interstitialAd.loadAd(request3);
    }

    @Override
    public void showBottomBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bottom_banner.setVisibility(View.VISIBLE);
                AdRequest.Builder builder = new AdRequest.Builder();
                builder.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
                AdRequest request = builder.build();
                bottom_banner.loadAd(request);
            }
        });

    }

    private void loadInterstitialAd() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AdRequest.Builder builder = new AdRequest.Builder();
                builder.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
                AdRequest request = builder.build();
                interstitialAd.loadAd(request);

            }
        });
    }

    @Override
    public void showInterstitial() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!interstitialAd.isLoaded()) {
                    AdRequest.Builder builder = new AdRequest.Builder();
                    builder.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
                    AdRequest request = builder.build();
                    interstitialAd.loadAd(request);
                }
                interstitialAd.show();
            }
        });

    }

    @Override
    public void hideBottomBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bottom_banner.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void showTopBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                top_banner.setVisibility(View.VISIBLE);
                AdRequest.Builder builder = new AdRequest.Builder();
                builder.addTestDevice(activity.getResources().getString(R.string.testDeviceId));
                AdRequest request = builder.build();
                top_banner.loadAd(request);
            }
        });
    }
}
