package com.ayoub.wallpaper.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoub.wallpaper.AdsData.AdsLoaded;
import com.ayoub.wallpaper.Intromanager;
import com.ayoub.wallpaper.R;
import com.bumptech.glide.Glide;
import com.vimalcvs.materialrating.MaterialRatingApp;

public class MainActivity extends AppCompatActivity {
    ImageView img1,img2,img3;
    private ViewPager viewPager;
    private Intromanager intromanager;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView[] dots;
    Button next,skip;
    private LinearLayout dotsLayout;
    private int[] layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1;
        view1 = inflater.inflate(R.layout.slider_1, null);
         img1=view1.findViewById(R.id.slide1);
        View view2;
        view2 = inflater.inflate(R.layout.slider_1, null);
        img2=view2.findViewById(R.id.slide1);
        View view3;
        view3 = inflater.inflate(R.layout.slider_1, null);
        img3=view3.findViewById(R.id.slide1);
        Glide.with(MainActivity.this).load(SplashActivity.image_silder1).into(img1);
        Glide.with(MainActivity.this).load(SplashActivity.image_silder2).into(img2);
        Glide.with(MainActivity.this).load(SplashActivity.image_silder3).into(img3);
        // Importing the intromanager class, which will check if this is the first time the application has been launched.
        intromanager = new Intromanager(this);
        if(!intromanager.Check())
        {

            intromanager.setFirst(false);
            Intent i = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(i);
            finish();
        }
        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);

        //Objects
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotsLayout=(LinearLayout)findViewById(R.id.layoutDots);
        skip=(Button)findViewById(R.id.btn_skip);
        next = (Button)findViewById(R.id.btn_next);
        layouts = new int[]{R.layout.slider_1,R.layout.slider_2,R.layout.slider_3};
        addBottomDots(0);
        changeStatusBarColor();
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        //For the next and previous buttons
        skip.setOnClickListener(view -> {
            AdsLoaded.loadaAdInter(MainActivity.this, new AdsLoaded.inter() {
                @Override
                public void onfinished() {
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i);
                    finish();
                }
            });

        });

        next.setOnClickListener(view -> {
            int current = getItem(+1);
            if(current<layouts.length)
            {
                viewPager.setCurrentItem(current);
            }
            else
            {

                AdsLoaded.loadaAdInter(MainActivity.this, new AdsLoaded.inter() {
                    @Override
                    public void onfinished() {
                        Intent i = new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });

    }

    //Giving the dots functionality
    @SuppressLint("ResourceAsColor")
    private void addBottomDots(int position)
    {

        dots = new TextView[layouts.length];
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(R.color.inactive);
            dotsLayout.addView(dots[i]);
        }
        if(dots.length>0)
            dots[position].setTextColor(R.color.active);
    }

    private int getItem(int i)
    {
        return viewPager.getCurrentItem() + i;
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addBottomDots(position);
            if(position==layouts.length-1)
            {
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);
            }
            else
            {
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    //PagerAdapter class which will inflate our sliders in our ViewPager
    public class ViewPagerAdapter extends PagerAdapter
    {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup myContainer, int mPosition) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[mPosition],myContainer,false);
            myContainer.addView(v);
            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View mView, Object mObject) {
            return mView==mObject;
        }

        @Override
        public void destroyItem(ViewGroup mContainer, int mPosition, Object mObject) {
            View v = (View) mObject;
            mContainer.removeView(v);
        }
    }
}