package com.ayoub.wallpaper.Activities;

import android.os.Bundle;

import com.ayoub.wallpaper.AdsData.AdsLoaded;
import com.ayoub.wallpaper.R;
import com.ayoub.wallpaper.modAdapter;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityMods extends AppCompatActivity {
     modAdapter collectionAdapter;
    RecyclerView ImageRecycle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);

         ImageRecycle=findViewById(R.id.rw);
        AdsLoaded.loadbanner(this);
         collectionAdapter =new modAdapter(SplashActivity.list,this);
        ImageRecycle.setAdapter(collectionAdapter);
        ImageRecycle.setHasFixedSize(true);
        ImageRecycle.setLayoutManager(new GridLayoutManager(this,1));
    }


}