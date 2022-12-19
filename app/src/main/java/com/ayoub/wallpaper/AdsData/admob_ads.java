package com.ayoub.wallpaper.AdsData;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.MyApplication;
import com.ayoub.wallpaper.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;


public class admob_ads extends  Activity {
    static InterstitialAd mInterstitialAd;
    public interface  inter{
        void onfinished();
    }
    public static void loadBanner(Activity activity) {
        AdView adView = new AdView(activity);
        adView.setAdUnitId(SplashActivity.BANNER_ADMOB);
        adView.setAdSize(getAdSize(activity));
        ((RelativeLayout) activity.findViewById(R.id.bannerAd)).addView(adView);
        AdRequest build = new AdRequest.Builder().build();
        adView.loadAd(build);
    }

    public static AdSize getAdSize(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }
    static RewardedInterstitialAd rewardedInterstitialAd;
    public static void RewardeLoad(Activity context,inter i) {
        // Use the test ad unit ID to load an ad.
        RewardedInterstitialAd.load(context, SplashActivity.ADMOB_REWARDE,
                new AdRequest.Builder().build(),  new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        rewardedInterstitialAd = ad;
                        Log.e(TAG, "onAdLoaded");
                        Activity activityContext = context;
                        rewardedInterstitialAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                            }
                        });
                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            /** Called when the ad failed to show full screen content. */
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                i.onfinished();
                              }

                            /** Called when ad showed the full screen content. */
                            @Override
                            public void onAdShowedFullScreenContent() {

                            }

                            /** Called when full screen content is dismissed. */
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                i.onfinished();
                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        i.onfinished();
                    }
                });
    }




    public static void loadinter(Activity context,final inter finished){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, SplashActivity.INTERSTITIAL_ADMOB, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        showinter(context);
                        Log.i(TAG, "onAdLoaded");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                finished.onfinished();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                finished.onfinished();

                                // Called when fullscreen content failed to show.

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;

                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error


                    }
                });
    }


    private static void showinter(Activity context){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }




    private static void populateNativeADView(NativeAd nativeAd, NativeAdView adView)
    {
        // Set the media view.
        adView.setMediaView(adView.findViewById(R.id.ad_media));

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);
    }
    public static void loadNative (Activity activity){
        AdLoader.Builder builder = new AdLoader.Builder(activity, SplashActivity.NATIVE_ADMOB)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(@NonNull NativeAd nativeAd) {

                        try {
                            NativeAdView nativeAdView = (NativeAdView) activity.getLayoutInflater().inflate(R.layout.layout_native_ad, null);
                            populateNativeADView(nativeAd, nativeAdView);
                            FrameLayout adlayout=activity.findViewById(R.id.frame_native);
                            adlayout.removeAllViews();
                            adlayout.addView(nativeAdView);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }
                });

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);

            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }


}

