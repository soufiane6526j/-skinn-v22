package com.ayoub.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class App_item_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    public  List<ItemModel> appsList;



    public App_item_Adapter(Context context, List<ItemModel> appsList) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.appsList=appsList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AppViewHolder(this.inflater.inflate(R.layout.app_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((AppViewHolder) viewHolder).bindAdData(appsList.get(i));

    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return appsList.size();
    }
    public class AppViewHolder extends RecyclerView.ViewHolder {
        TextView appName;
        Button install;
        ImageView appIcon;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appName=itemView.findViewById(R.id.appName);
            install=itemView.findViewById(R.id.install);
            appIcon=itemView.findViewById(R.id.appIcon);
        }

        private void bindAdData(ItemModel item) {
            Glide.with(context).load(item.getIcon()).into(appIcon);
           appName.setText(item.getName());
            install.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getAppUrl()));
                    context.startActivity(it);
                }
            });


        }
    }
}