package com.ayoub.wallpaper.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ayoub.wallpaper.AdsData.AdsLoaded;
import com.ayoub.wallpaper.App_item_Adapter;
import com.ayoub.wallpaper.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.vimalcvs.materialrating.MaterialRatingApp;

public class MainActivity2 extends AppCompatActivity {
    AppCompatButton st,rt,fv;
    App_item_Adapter app_item_adapter;
    RecyclerView rc1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rc1=findViewById(R.id.rc);
        app_item_adapter=new App_item_Adapter(MainActivity2.this,SplashActivity.appList);
        rc1.setAdapter(app_item_adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true);
        rc1.setLayoutManager(linearLayoutManager);
        AdsLoaded.LoadNative(this);
        st=findViewById(R.id.btnstart);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsLoaded.loadaAdInter(MainActivity2.this, new AdsLoaded.inter() {
                    @Override
                    public void onfinished() {
                        startActivity(new Intent(getApplicationContext(), ActivityMods.class));
                        Animatoo.animateZoom(MainActivity2.this);
                     }
                });
            }
        });



    }
    int i=0;
    @Override
    public void onBackPressed() {
        if (i==0){
            MaterialRatingApp materialRatingApp = new MaterialRatingApp (MainActivity2.this);
            materialRatingApp.showNow(getSupportFragmentManager(), "");
            i++;
        }else if(i==1) {
            Toast.makeText(this, "press back again to exit ", Toast.LENGTH_SHORT).show();
            i++;
        }else if(i==2) {
            super.onBackPressed();
        }

    }
}