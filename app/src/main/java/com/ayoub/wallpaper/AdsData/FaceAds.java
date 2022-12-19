package com.ayoub.wallpaper.AdsData;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

public class FaceAds {

    public interface AdFinished {
        void onAdFinished();
    }
    public static void fanRewa (Activity activity,AdFinished add){
        RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(activity, SplashActivity.REWARD_FACEBOOK);

        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override

            public void onError(Ad ad, AdError adError) {
                add.onAdFinished();

            }

            @Override
            public void onAdLoaded(Ad ad) {
                rewardedVideoAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onRewardedVideoCompleted() {
                add.onAdFinished();
            }

            @Override
            public void onRewardedVideoClosed() {

            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
    }

    public static void showBanner(Activity activity) {
        RelativeLayout adContainer=activity.findViewById(R.id.bannerAd);
        AdView adView = new AdView(activity, SplashActivity.BANNER_FACEBOOK, AdSize.BANNER_HEIGHT_90);
        adContainer.addView(adView);
        adView.loadAd();
    }


    public static void showInter(Activity activity, AdFinished adFinished) {

        final InterstitialAd interstitialAd = new InterstitialAd(activity, SplashActivity.INTERSTIAL_FACEBOOK);
        final InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                adFinished.onAdFinished();

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                adFinished.onAdFinished();

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

    }



    private static NativeAd nativeAd;
    public static   void loadFacebookNative(Activity context) {
        nativeAd = new NativeAd(context, SplashActivity.NATIVE_FACEBOOK);

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
            }

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                inflateAd(nativeAd,context);
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        };

        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                        .build());
    }
    private static void inflateAd(NativeAd nativeAd,Activity activity) {

        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        NativeAdLayout nativeAdLayout = activity.findViewById(R.id.native_ad_container);
        nativeAdLayout.setVisibility(View.VISIBLE);
        LayoutInflater inflater = LayoutInflater.from(activity);
        // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.facebook_native, nativeAdLayout, false);
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = activity.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and CTA button to listen for clicks.
        nativeAd.registerViewForInteraction(
                adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }


}
