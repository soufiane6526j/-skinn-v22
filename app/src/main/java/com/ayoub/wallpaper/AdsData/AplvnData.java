package com.ayoub.wallpaper.AdsData;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.R;


public class AplvnData {
    public static MaxInterstitialAd interstitialAd;
    private static MaxNativeAdLoader nativeAdLoader;
    private static  MaxAd nativeAd;
    private static  MaxAdView adView;


    @SuppressLint("ResourceAsColor")
    static void applovinbanner(Activity activity)
    {
        adView = new MaxAdView( SplashActivity.BANNER_APPLOVIN, activity );
        adView.setListener(new MaxAdViewAdListener() {
            @Override
            public void onAdExpanded(MaxAd ad) {

            }

            @Override
            public void onAdCollapsed(MaxAd ad) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {

            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {

            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

            }
        });

        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = activity.getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx, Gravity.BOTTOM) );

        // Set background or background color for banners to be fully functional
        adView.setBackgroundColor(R.color.white);

        ViewGroup rootView = activity.findViewById( android.R.id.content );
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();
    }
    public static  void applovinmrec(Activity context) {
        FrameLayout nativeAdContainer = context.findViewById( R.id.frame_native );
        nativeAdLoader = new MaxNativeAdLoader( SplashActivity.NATIVE_APPLOVIN, context );
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView maxNativeAdView,final MaxAd maxAd) {
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = maxAd;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                int heightPx = context.getResources().getDimensionPixelSize( R.dimen.native_height );
                maxNativeAdView.setLayoutParams(new FrameLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,heightPx));
                nativeAdContainer.addView( maxNativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(String s, MaxError maxError) {
                super.onNativeAdLoadFailed(s, maxError);
             }

            @Override
            public void onNativeAdClicked(MaxAd maxAd) {
                super.onNativeAdClicked(maxAd);
            }
        });
        nativeAdLoader.loadAd();
    }


     private static MaxRewardedAd rewardedAd;
    public static  void applovinrew(Activity activity,OnAdsdone adsdone){
        rewardedAd = MaxRewardedAd.getInstance( SplashActivity.Applovin_REWARDE, activity );
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {

            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                showrew();
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {
                adsdone.adsdone();
            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                rewardedAd.loadAd();
                adsdone.adsdone();
                Toast.makeText(activity, "load +" +error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                rewardedAd.loadAd();
                Toast.makeText(activity, "display +" +error, Toast.LENGTH_SHORT).show();
                adsdone.adsdone();
            }
        });

        rewardedAd.loadAd();
    }
      public static  void applovininter(Activity activity,OnAdsdone adsdone) {
        try {
            interstitialAd = new MaxInterstitialAd(SplashActivity.INTERSTITIAL_APPLOVIN, activity);
            interstitialAd.setListener(new MaxAdListener() {
                @Override
                public void onAdLoaded(MaxAd ad) {
                    showAds();
                    Log.e("Applovin", "ADS SID LOADED");
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    Log.e("Applovin", "ADS SID onAdDisplayed");
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    adsdone.adsdone();
                    Log.e("Applovin", "ADS SID onAdHidden");
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                    Log.e("Applovin", "ADS SID onAdClicked");
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                    adsdone.adsdone();
                    Log.e("Applovin", "ADS SID onAdLoadFailed" + error.getCode() + " :: " + error.getMessage());
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    adsdone.adsdone();
                    Log.e("Applovin", "ADS SID onAdDisplayFailed" + error.getMessage() + " :: " + error.getCode());
                }
            });
            // Load the first ad
            interstitialAd.loadAd();
        }
        catch (Exception e){

        }

    }
    private static void showrew() {

        if (rewardedAd != null && rewardedAd.isReady()) {
            rewardedAd.showAd();
        }
    }
    public static void showAds() {

        if (interstitialAd != null && interstitialAd.isReady()) {
            interstitialAd.showAd();
        }
    }
    public  interface OnAdsdone{
        void adsdone();
    }

}
