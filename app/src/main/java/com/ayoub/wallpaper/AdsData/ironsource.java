package com.ayoub.wallpaper.AdsData;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.R;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.BannerListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;


public class ironsource extends Activity {
    public  interface inter{
        void inter();
    }

    public static IronSourceBannerLayout banner;
    private static final String TAG = "Utils";
    public static void showBannerView(Activity context) {
        IronSource.init(context, SplashActivity.IRONSOURCE_ID,IronSource.AD_UNIT.BANNER);

        RelativeLayout adContainer=context.findViewById(R.id.bannerAd);
        adContainer.setVisibility(View.VISIBLE);
        if (banner == null) {
            banner = IronSource.createBanner(context, ISBannerSize.SMART);
            banner.setBannerListener(new BannerListener() {
                @Override
                public void onBannerAdLoaded() {
                    // Called after a banner ad has been successfully loaded
                    Log.e(TAG, "onBannerAdLoaded: ");
                    destroyBanner(banner, adContainer);
                    adContainer.addView(banner);
                }
                @Override
                public void onBannerAdLoadFailed(IronSourceError error) {
                    // Called after a banner has attempted to load an ad but failed.
                    Log.e(TAG, "onBannerAdLoadFailed: " + error);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adContainer.removeAllViews();
                        }
                    });
                }

                @Override
                public void onBannerAdClicked() {
                    // Called after a banner has been clicked.
                }

                @Override
                public void onBannerAdScreenPresented() {
                    // Called when a banner is about to present a full screen content.
                }

                @Override
                public void onBannerAdScreenDismissed() {
                    // Called after a full screen content has been dismissed
                }

                @Override
                public void onBannerAdLeftApplication() {
                    // Called when a user would be taken out of the application context.
                }
            });

            IronSource.loadBanner(banner);
        } else {
            destroyBanner(banner, adContainer);
            adContainer.addView(banner);
        }
    }
    private static void destroyBanner(View view, RelativeLayout relativeLayout) {
        if (view != null && view.getParent() != null)
            ((ViewGroup) view.getParent()).removeView(view);
        if (relativeLayout != null && relativeLayout.getChildCount() > 0)
            relativeLayout.removeAllViews();

    }
    public static void ironreawrde(Activity activity,inter i){
        IronSource.init(activity, SplashActivity.IRONSOURCE_ID, IronSource.AD_UNIT.REWARDED_VIDEO);
        IronSource.setRewardedVideoListener(new RewardedVideoListener() {
            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoAdClosed() {


            }

            @Override
            public void onRewardedVideoAvailabilityChanged(boolean b) {

            }

            @Override
            public void onRewardedVideoAdStarted() {

            }

            @Override
            public void onRewardedVideoAdEnded() {

            }

            @Override
            public void onRewardedVideoAdRewarded(Placement placement) {
                i.inter();
            }

            @Override
            public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
                i.inter();

            }

            @Override
            public void onRewardedVideoAdClicked(Placement placement) {

            }
        });


        IronSource.loadRewardedVideo();
        IronSource.showRewardedVideo("DefaultRewardedVideo");
    }

    public static void ironinter(Activity activity,inter interr){
        IronSource.init(activity, SplashActivity.IRONSOURCE_ID, IronSource.AD_UNIT.INTERSTITIAL);

        IronSource.setInterstitialListener(new InterstitialListener() {

            @Override
            public void onInterstitialAdReady() {
                IronSource.showInterstitial("DefaultInterstitial");

            }

            @Override
            public void onInterstitialAdLoadFailed(IronSourceError error) {
                interr.inter();
            }

            @Override
            public void onInterstitialAdOpened() {
            }

            @Override
            public void onInterstitialAdClosed() {
                interr.inter();
            }

            @Override
            public void onInterstitialAdShowFailed(IronSourceError error) {
                interr.inter();
            }

            @Override
            public void onInterstitialAdClicked() {
            }

            @Override
            public void onInterstitialAdShowSucceeded() {
            }
        });
        IronSource.loadInterstitial();
    }



}
