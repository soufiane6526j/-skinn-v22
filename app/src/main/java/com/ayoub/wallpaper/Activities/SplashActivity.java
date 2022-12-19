package com.ayoub.wallpaper.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ayoub.wallpaper.ImageModel;
import com.ayoub.wallpaper.ItemModel;
import com.ayoub.wallpaper.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    public static  List<ImageModel> list;
    public static List<ItemModel> appList;
    public static boolean showCpa;
    public static String CpaImage;
    public static String CpaTitle;
    public static String CpaSousTitle;
    public static String CpaUrl;



    public static String image_silder1;
    public static String image_silder2;
    public static String image_silder3;
    public  static String Json_Link="https://ayoubouabi6.github.io/tazateams/test.json";
    public  static  String openApp="ca-app-pub-3940256099942544/3419835294";
    public static String NATIVE_YANDEX;
    public static String BANNER_YANDEX;
    public static String BANNER_ADMOB;
    public static String NATIVE_ADMOB,INTERSTITIAL_ADMOB,INTERSTITIAL_YANDEX,YANDEX_REWARDE,ADMOB_REWARDE;
    public static String native_type,rewarde_type, banner_type,inter_type,IRONSOURCE_ID,unity_id;
    public static String NATIVE_APPLOVIN,NATIVE_FACEBOOK;
    public static String BANNER_APPLOVIN,BANNER_FACEBOOK;
    public static String Applovin_REWARDE,REWARD_FACEBOOK;
    public static String INTERSTITIAL_APPLOVIN,INTERSTIAL_FACEBOOK;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        list=new ArrayList<>();
        appList =new ArrayList<>();

         if(intercheker()){
             getDatad();
         }else{
             ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
             progressDialog.setMessage("error,No internet Connection");
             progressDialog.show();
             progressDialog.setCancelable(false);
         }


    }
    boolean intercheker(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }
     public void getDatad() {
        Volley.newRequestQueue(this).add(new StringRequest(0, Json_Link, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject jSONObjectRoot = new JSONObject(response);
                            JSONObject object = jSONObjectRoot.getJSONObject("ADS");

                            CpaImage=object.getString("cpa_image");
                            CpaTitle=object.getString("cpa_title");
                            CpaSousTitle=object.getString("cpa_sous_title");
                            CpaUrl=object.getString("cpa_url");
                            showCpa=object.getBoolean("show_cpa");

                            image_silder1=object.getString("image_silder1");
                            image_silder2=object.getString("image_silder2");
                            image_silder3=object.getString("image_silder3");

                            banner_type=object.getString("banner_type");
                            inter_type=object.getString("inter_type");
                            native_type=object.getString("native_type");
                            rewarde_type=object.getString("reward_type");


                            IRONSOURCE_ID=object.getString("ironsource_id");
                            unity_id=object.getString("unity_id");

                            BANNER_YANDEX = object.getString("banner_yandex");
                            NATIVE_YANDEX = object.getString("native_yandex");
                            INTERSTITIAL_YANDEX = object.getString("interstitial_yandex");
                            YANDEX_REWARDE = object.getString("rewarde_yandex");



                            openApp = object.getString("openapp_admob");
                            BANNER_ADMOB = object.getString("banner_admob");
                            NATIVE_ADMOB = object.getString("native_admob");
                            INTERSTITIAL_ADMOB = object.getString("interstitial_admob");
                            ADMOB_REWARDE = object.getString("rewarde_admob");

                            BANNER_APPLOVIN = object.getString("applovin_banner");
                            INTERSTITIAL_APPLOVIN = object.getString("applovin_interstitial");
                            Applovin_REWARDE = object.getString("applovin_reward");
                             NATIVE_APPLOVIN = object.getString("applovin_native");

                            BANNER_FACEBOOK = object.getString("facebook_banner");
                            INTERSTIAL_FACEBOOK = object.getString("facebook_interstitial");
                            REWARD_FACEBOOK = object.getString("facebook_reward");
                             NATIVE_FACEBOOK = object.getString("facebook_native");


                            JSONArray guided = jSONObjectRoot.getJSONArray("skins");
                            //Toast.makeText(SplashActivity.this, guided.length(), Toast.LENGTH_SHORT).show();
                             for (int i = 0; i < guided.length(); i++) {
                                // creating a new json object and
                                JSONObject responseObj = guided.getJSONObject(i);
                                 String img=responseObj.getString("imageUrl");
                                String ttl=responseObj.getString("title");
                                String like=responseObj.getString("likeNumbers");
                                String cmnt=responseObj.getString("commentNumbers");
                                String download=responseObj.getString("downloadNumbers");
                                String desc=responseObj.getString("description");
                                ImageModel myList=new ImageModel(img,ttl,like,cmnt,download,desc);
                                list.add(myList);
                            }
                            JSONArray apps = jSONObjectRoot.getJSONArray("Apps");
                             for (int i = 0; i < apps.length(); i++) {
                                // creating a new json object and
                                JSONObject responseObj = apps.getJSONObject(i);
                                String ttl=responseObj.getString("title");
                                String img=responseObj.getString("image");
                                String url=responseObj.getString("url");
                                ItemModel myList=new ItemModel(img,url,ttl);
                                appList.add(myList);
                            }

                            Handler h=new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    finish();
                                }
                            },3000);


                        } catch (JSONException J){
                            Toast.makeText(SplashActivity.this, J.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("wld", "onResponse: "+J.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SplashActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                })
        );

    }


}