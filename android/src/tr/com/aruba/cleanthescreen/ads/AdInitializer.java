package tr.com.aruba.cleanthescreen.ads;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.*;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.xlip.threedtemp.Interfaces.AndroidUnit;

import java.util.Arrays;
import java.util.List;

import tr.com.aruba.cleanthescreen.R;

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
    List<String> testDeviceIds;


    public AdInitializer(Activity activity) {
        this.activity = activity;
        this.bottom_banner_id = activity.getResources().getString(R.string.bottom_banner_id);
        this.interstitial_id = activity.getResources().getString(R.string.interstitial_id);

        this.testDeviceIds = Arrays.asList("33BE2250B43518CCDA7DE426D04EE231");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        MobileAds.setRequestConfiguration(configuration);
    }


    public View getView(View gameView) {

        setUpAds();

        RelativeLayout layout = new RelativeLayout(activity);
        layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        RelativeLayout.LayoutParams topBannerParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        topBannerParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        RelativeLayout.LayoutParams bottomBannerParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomBannerParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        layout.addView(bottom_banner, bottomBannerParams);
        layout.addView(top_banner, topBannerParams);

        return layout;
    }

    private void setUpAds() {
        //top_banner = new AdView(activity);

        top_banner = new AdView(activity);
        top_banner.setBackgroundColor(Color.TRANSPARENT);
        top_banner.setAdUnitId(bottom_banner_id);
        top_banner.setAdSize(AdSize.BANNER);
        top_banner.setVisibility(View.INVISIBLE);

        AdRequest.Builder builder1 = new AdRequest.Builder();

        AdRequest topbannerBuilder = builder1.build();
        top_banner.loadAd(topbannerBuilder);


        bottom_banner = new AdView(activity);
        bottom_banner.setBackgroundColor(Color.TRANSPARENT);
        bottom_banner.setAdUnitId(bottom_banner_id);
        bottom_banner.setAdSize(AdSize.BANNER);
        bottom_banner.setVisibility(View.INVISIBLE);

        AdRequest.Builder builder2 = new AdRequest.Builder();

        AdRequest bottomBannerBuilder = builder2.build();
        bottom_banner.loadAd(bottomBannerBuilder);





        AdRequest.Builder interstitialAdBuilder = new AdRequest.Builder();
        AdRequest interstitialAdRequest = interstitialAdBuilder.build();


        InterstitialAd.load(activity, interstitial_id, interstitialAdRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                AdInitializer.this.interstitialAd = interstitialAd;

            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }
        });

    }

    @Override
    public void showBottomBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bottom_banner.setVisibility(View.VISIBLE);
                AdRequest.Builder builder = new AdRequest.Builder();
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
                //builder2.addTestDevice("AFDD6285E569D06FB511CBA52DCF48F3");
                AdRequest request = builder.build();


                InterstitialAd.load(activity, interstitial_id, request, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        AdInitializer.this.interstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }
                });

            }
        });
    }

    @Override
    public void showInterstitial() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                interstitialAd.show(activity);
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
    public void hideTopBanner() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                top_banner.setVisibility(View.INVISIBLE);
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
                AdRequest request = builder.build();
                top_banner.loadAd(request);
            }
        });
    }
}
