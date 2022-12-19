package com.ayoub.wallpaper.AdsData;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.ayoub.wallpaper.Activities.SplashActivity;
import com.ayoub.wallpaper.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class AdsLoaded {



    public static void rewardeLoaded(Activity activity,inter l){

        AlertDialog.Builder alert=new AlertDialog.Builder(activity, R.style.MyDialogTheme);
        alert.setMessage("To download  this you need to watch ad video");
        alert.setPositiveButton("open", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProgressDialog progressDialog = new ProgressDialog(activity);
                progressDialog.show();
                progressDialog.setContentView(R.layout.loading_dialog);
                Handler h=new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (SplashActivity.rewarde_type.equalsIgnoreCase("applovin")){
                            AplvnData.applovinrew(activity, new AplvnData.OnAdsdone() {
                                @Override
                                public void adsdone() {
                                    l.onfinished();
                                    progressDialog.dismiss();
                                    alert.setCancelable(true);
                                }
                            });

                        }else if(SplashActivity.rewarde_type.equalsIgnoreCase("unity")){
                            UnityAds.DisplayRewarde(activity, new UnityAds.inter() {
                                @Override
                                public void inter() {
                                    l.onfinished();
                                    progressDialog.dismiss();
                                    alert.setCancelable(true);

                                }
                            });
                        }else if(SplashActivity.rewarde_type.equalsIgnoreCase("ironsource")){
                            ironsource.ironreawrde(activity, new ironsource.inter() {
                                @Override
                                public void inter() {
                                    l.onfinished();
                                    progressDialog.dismiss();
                                }
                            });
                        }else if (SplashActivity.rewarde_type.equalsIgnoreCase("facebook")){
                            FaceAds.fanRewa(activity, new FaceAds.AdFinished() {
                                @Override
                                public void onAdFinished() {
                                    l.onfinished();
                                    progressDialog.dismiss();
                                    alert.setCancelable(true);

                                }
                            });
                        }else if (SplashActivity.rewarde_type.equalsIgnoreCase("admob")){
                            admob_ads.RewardeLoad(activity, new admob_ads.inter() {
                                @Override
                                public void onfinished() {
                                    l.onfinished();
                                    progressDialog.dismiss();
                                    alert.setCancelable(true);

                                }
                            });

                        }else if (SplashActivity.rewarde_type.equalsIgnoreCase("yandex")){
                            yandex_ads.rewarde(activity, new yandex_ads.interr() {
                                @Override
                                public void inter() {
                                    l.onfinished();
                                    progressDialog.dismiss();
                                    alert.setCancelable(true);

                                }
                            });

                        }
                    }
                },1000);
                progressDialog.setCancelable(false);
            }
        });
        alert.show();



    }


    public static void loadbanner (Activity activity){
        if (SplashActivity.banner_type.equalsIgnoreCase("applovin")){
            AplvnData.applovinbanner(activity);
        }else if(SplashActivity.banner_type.equalsIgnoreCase("unity")){
            UnityAds.loadbanner(activity);
        }else if(SplashActivity.banner_type.equalsIgnoreCase("ironsource")){
            ironsource.showBannerView(activity);
        }else if(SplashActivity.banner_type.equalsIgnoreCase("facebook")){
            FaceAds.showBanner(activity);
        }else if(SplashActivity.banner_type.equalsIgnoreCase("admob")){
            admob_ads.loadBanner(activity);
        }else if(SplashActivity.banner_type.equalsIgnoreCase("yandex")){
            yandex_ads.banner(activity);
        }
    }

    public static void LoadNative (Activity  activity){
        if (SplashActivity.native_type.equalsIgnoreCase("applovin")){

            AplvnData.applovinmrec(activity);
        }else if (SplashActivity.native_type.equalsIgnoreCase("facebook")){

            FaceAds.loadFacebookNative(activity);
        }else if (SplashActivity.native_type.equalsIgnoreCase("admob")){

            admob_ads.loadNative(activity);
        }else if (SplashActivity.native_type.equalsIgnoreCase("yandex")){

            yandex_ads.loadnative(activity);
        }
    }

    public interface  inter{
        void onfinished();
    }
    public   static void loadaAdInter(Activity activity,inter i)
    {

        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loading_dialog);
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (SplashActivity.inter_type.equalsIgnoreCase("applovin")){
                    AplvnData.applovininter(activity, new AplvnData.OnAdsdone() {
                        @Override
                        public void adsdone() {
                            progressDialog.dismiss();
                            i.onfinished();
                        }
                    });

                }else if (SplashActivity.inter_type.equalsIgnoreCase("unity")){
                    UnityAds.DisplayInterstitialAd(activity, new UnityAds.inter() {
                        @Override
                        public void inter() {
                            progressDialog.dismiss();
                            i.onfinished();
                        }
                    });
                }else if (SplashActivity.inter_type.equalsIgnoreCase("ironsource")){
                    ironsource.ironinter(activity, new ironsource.inter() {
                        @Override
                        public void inter() {
                            progressDialog.dismiss();
                            i.onfinished();
                        }
                    });
                }else if(SplashActivity.inter_type.equalsIgnoreCase("facebook")){
                    FaceAds.showInter(activity, new FaceAds.AdFinished() {
                        @Override
                        public void onAdFinished() {
                            progressDialog.dismiss();
                            i.onfinished();
                        }
                    });
                }else if(SplashActivity.inter_type.equalsIgnoreCase("admob")){
                    admob_ads.loadinter(activity, new admob_ads.inter() {
                        @Override
                        public void onfinished() {
                            progressDialog.dismiss();
                            i.onfinished();
                        }
                    });

                }else if(SplashActivity.inter_type.equalsIgnoreCase("yandex")){
                    yandex_ads.inter(activity, new yandex_ads.interr() {
                        @Override
                        public void inter() {
                            progressDialog.dismiss();
                            i.onfinished();
                        }
                    });

                }
            }
        },1000);
        progressDialog.setCancelable(false);
    }

}
