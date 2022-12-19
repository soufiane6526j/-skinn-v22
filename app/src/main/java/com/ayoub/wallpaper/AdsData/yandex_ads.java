package com.ayoub.wallpaper.AdsData;

import android.app.Activity;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.R;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;
import com.yandex.mobile.ads.nativeads.NativeAd;
import com.yandex.mobile.ads.nativeads.NativeAdException;
import com.yandex.mobile.ads.nativeads.NativeAdRequestConfiguration;
import com.yandex.mobile.ads.nativeads.NativeAdView;
import com.yandex.mobile.ads.nativeads.NativeAdViewBinder;
import com.yandex.mobile.ads.nativeads.SliderAd;
import com.yandex.mobile.ads.nativeads.SliderAdLoadListener;
import com.yandex.mobile.ads.nativeads.SliderAdLoader;
import com.yandex.mobile.ads.nativeads.template.NativeBannerView;
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;

import java.util.List;


public class yandex_ads extends Activity{

    public static  void loadnative(Activity c){
        final SliderAdLoader sliderAdLoader = new SliderAdLoader(c);
        sliderAdLoader.setSliderAdLoadListener(new SliderAdLoadListener() {
            @Override
            public void onSliderAdLoaded(@NonNull final SliderAd sliderAd) {
                NativeAdView mNativeAdView;
                mNativeAdView = (NativeAdView) c.findViewById(R.id.native_slider_ad_container);
                try {
                    final NativeAdViewBinder sliderViewBinder =
                            new NativeAdViewBinder.Builder(mNativeAdView).build();
                    sliderAd.bindSliderAd(sliderViewBinder);
                    final List<NativeAd> nativeAds = sliderAd.getNativeAds();
                    for (final NativeAd nativeAd : nativeAds) {
                        final NativeBannerView nativeBannerView = new NativeBannerView(c);
                        nativeBannerView.setAd(nativeAd);
                        mNativeAdView.addView(nativeBannerView);
                    }
                } catch (final NativeAdException exception) {
                    //log error
                }
            }

            @Override
            public void onSliderAdFailedToLoad(@NonNull final AdRequestError error) {
                //log error
            }
        });

        final NativeAdRequestConfiguration nativeAdRequestConfiguration =
                new NativeAdRequestConfiguration.Builder(SplashActivity.NATIVE_YANDEX).build();
        sliderAdLoader.loadSlider(nativeAdRequestConfiguration);
    }

    public  static void banner(Activity activity){
        BannerAdView mBannerAdView =new BannerAdView(activity);
        ((RelativeLayout) activity.findViewById(R.id.bannerAd)).addView(mBannerAdView);
        //mBannerAdView.setAdSize(AdSize.BANNER_320x50);
        mBannerAdView.setAdUnitId(SplashActivity.BANNER_YANDEX);
        mBannerAdView.setAdSize(AdSize.flexibleSize(320,50));
        final AdRequest adRequest = new AdRequest.Builder().build();
        mBannerAdView.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(@NonNull final AdRequestError error) {
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });
        mBannerAdView.loadAd(adRequest);
    }
    public interface interr{
        void inter();
    }
    public static void inter(Activity activity,interr i){
        InterstitialAd mInterstitialAd;
        mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(SplashActivity.INTERSTITIAL_YANDEX);

        // Creating an ad targeting object.
        final AdRequest adRequest = new AdRequest.Builder().build();

        //  Registering a listener to track events in the ad.
        mInterstitialAd.setInterstitialAdEventListener(new InterstitialAdEventListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(AdRequestError adRequestError) {
                i.inter();
            }

            @Override
            public void onAdShown() {
            }

            @Override
            public void onAdDismissed() {
                i.inter();
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });

        // Loading ads.
        mInterstitialAd.loadAd(adRequest);
    }
    public static void rewarde(Activity activity,interr i){
        RewardedAd mRewardedAd = new RewardedAd(activity);
        mRewardedAd.setAdUnitId(SplashActivity.YANDEX_REWARDE);

        // Creating an ad targeting object.
        final AdRequest adRequest = new AdRequest.Builder().build();

        // Registering a listener to track events in the ad.
        mRewardedAd.setRewardedAdEventListener(new RewardedAdEventListener() {


            @Override
            public void onRewarded(final Reward reward) {
                i.inter();
            }

            @Override
            public void onAdClicked() {

            }

            @Override
            public void onAdLoaded() {
                mRewardedAd.show();
            }

            @Override
            public void onAdFailedToLoad(final AdRequestError adRequestError) {
                i.inter();
            }

            @Override
            public void onAdShown() {
            }

            @Override
            public void onAdDismissed() {
            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {

            }
        });

        // Loading ads.
        mRewardedAd.loadAd(adRequest);
    }
}
