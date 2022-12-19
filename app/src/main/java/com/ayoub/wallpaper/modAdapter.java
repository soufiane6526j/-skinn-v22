package com.ayoub.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayoub.wallpaper.Activities.ShowMod;
import com.ayoub.wallpaper.AdsData.AdsLoaded;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

import java.util.List;

public class modAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public static List<ImageModel> list;
    int pos;
    Activity contex;
    public static String img,ttl1,desc;
    private static final int ITEM_VIEW = 0;
    private static final int AD_VIEW = 1;
    private static final int ITEM_FEED_COUNT = 4;
    public modAdapter(List<ImageModel> list, Activity c){
        this.list=list;
        this.contex=c;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(contex);

        if (viewType == ITEM_VIEW) {
             View view = layoutInflater.inflate(R.layout.image_item, parent, false);
            return new MainViewHolder(view);
        } else if (viewType == AD_VIEW) {
             View view = layoutInflater.inflate(R.layout.layout_ad, parent, false);
            return new AdViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_VIEW) {
            pos = position - Math.round(position / ITEM_FEED_COUNT);
            ((MainViewHolder) holder).bindData(list.get(pos));

        } else if (holder.getItemViewType() == AD_VIEW) {
            ((AdViewHolder) holder).bindAdData();
        }


    }
    @Override
    public int getItemViewType(int position) {
        if ((position + 1) % ITEM_FEED_COUNT == 0) {
            return AD_VIEW;
        }
        return ITEM_VIEW;
    }
    @Override
    public int getItemCount() {
        if (list.size() > 0) {
            return list.size() + Math.round(list.size() / ITEM_FEED_COUNT);
        }
        return list.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ttl;
        TextView likes;
        TextView cmts;
        TextView dow;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.imgwall);
            ttl=itemView.findViewById(R.id.title);
            likes=itemView.findViewById(R.id.likes);
            cmts=itemView.findViewById(R.id.cmnts);
            dow=itemView.findViewById(R.id.dow);
        }
        private void bindData(ImageModel item){
            Glide.with(contex).load(item.getImage()).into(imageView);
            ttl.setText(item.getTtl());
            likes.setText(item.getLike());
            cmts.setText(item.getCmnt());
            dow.setText(item.getDownload());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AdsLoaded.loadaAdInter(contex, new AdsLoaded.inter() {
                        @Override
                        public void onfinished() {
                            Intent i=new Intent(contex, ShowMod.class);
                            img=item.getImage();
                            ttl1=item.getTtl();
                            desc=item.getDesc();
                            contex.startActivity(i);
                            Animatoo.animateZoom(contex);

                         }
                    });

                }
            });

        }
    }
    public class AdViewHolder extends RecyclerView.ViewHolder {




        public AdViewHolder(@NonNull View itemView) {
            super(itemView);


        }

        private void bindAdData() {
            try {
                AdsLoaded.LoadNative(contex);
            }catch (Exception ex){
                ex.printStackTrace();
            }


        }
    }

}
