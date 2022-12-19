package com.ayoub.wallpaper.AdsData;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.R;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAdsShowOptions;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;


public class UnityAds extends Activity  {
    private static String interPlacement = "Rewarded_Android";
    public  interface inter{
        void inter();
    }
    private static String bannerPlacement = "Banner_Android";

    public static void loadbanner(Activity activity){
        com.unity3d.ads.UnityAds.initialize(activity, SplashActivity.unity_id,true);
        UnityBannerLisnter BannerLisnter=new UnityBannerLisnter();
        RelativeLayout banner_ad=activity.findViewById(R.id.bannerAd);
        BannerView bannerView=new BannerView(activity,bannerPlacement, new UnityBannerSize(320, 50));
        bannerView.setListener(BannerLisnter);
        bannerView.load();
        banner_ad.addView(bannerView);


    }

    private static class UnityBannerLisnter implements  BannerView.IListener {


        @Override
        public void onBannerLoaded(BannerView bannerView) {

        }

        @Override
        public void onBannerClick(BannerView bannerView) {

        }

        @Override
        public void onBannerFailedToLoad(BannerView bannerView, BannerErrorInfo bannerErrorInfo) {

        }

        @Override
        public void onBannerLeftApplication(BannerView bannerView) {

        }
    }
    public static void DisplayRewarde(Activity activity,inter i) {
        com.unity3d.ads.UnityAds.initialize(activity, SplashActivity.unity_id,false);
        com.unity3d.ads.UnityAds.load(interPlacement, new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                com.unity3d.ads.UnityAds.show(activity, interPlacement, new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, com.unity3d.ads.UnityAds.UnityAdsShowError error, String message) {
                        i.inter();
                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, com.unity3d.ads.UnityAds.UnityAdsShowCompletionState state) {
                        i.inter();
                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, com.unity3d.ads.UnityAds.UnityAdsLoadError error, String message) {
                i.inter();
            }
        });
    }
    private static final String interPlacementt = "Interstitial_Android";

    public static void DisplayInterstitialAd(Activity activity,inter i) {
        com.unity3d.ads.UnityAds.initialize(activity, SplashActivity.unity_id,true);

        com.unity3d.ads.UnityAds.load(interPlacement, new IUnityAdsLoadListener() {
            @Override
            public void onUnityAdsAdLoaded(String placementId) {
                com.unity3d.ads.UnityAds.show(activity, interPlacementt, new UnityAdsShowOptions(), new IUnityAdsShowListener() {
                    @Override
                    public void onUnityAdsShowFailure(String placementId, com.unity3d.ads.UnityAds.UnityAdsShowError error, String message) {
                        i.inter();
                    }

                    @Override
                    public void onUnityAdsShowStart(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowClick(String placementId) {

                    }

                    @Override
                    public void onUnityAdsShowComplete(String placementId, com.unity3d.ads.UnityAds.UnityAdsShowCompletionState state) {
                        i.inter();
                    }
                });
            }

            @Override
            public void onUnityAdsFailedToLoad(String placementId, com.unity3d.ads.UnityAds.UnityAdsLoadError error, String message) {
                i.inter();
            }
        });
    }
}
