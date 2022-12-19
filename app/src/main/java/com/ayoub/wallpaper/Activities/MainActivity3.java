package com.ayoub.wallpaper.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayoub.wallpaper.AdsData.AdsLoaded;
import com.ayoub.wallpaper.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity3 extends AppCompatActivity {
    AppCompatButton an,ios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        an=findViewById(R.id.btnandroid);
        AdsLoaded.loadbanner(this);
        AdsLoaded.LoadNative(this);
        an.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsLoaded.loadaAdInter(MainActivity3.this, new AdsLoaded.inter() {
                    @Override
                    public void onfinished() {
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        Animatoo.animateZoom(MainActivity3.this);
                        finish();
                    }
                });

            }
        });
        ios=findViewById(R.id.btnios);
        ios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsLoaded.loadaAdInter(MainActivity3.this, new AdsLoaded.inter() {
                    @Override
                    public void onfinished() {
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        Animatoo.animateZoom(MainActivity3.this);
                        finish();
                    }
                });

            }
        });
    }
}