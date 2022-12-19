package com.ayoub.wallpaper.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.ayoub.wallpaper.AdsData.AdsLoaded;
import com.ayoub.wallpaper.R;
import com.ayoub.wallpaper.modAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ShowMod extends AppCompatActivity {
    ImageView img ;
    Button  btn;
    TextView tt,des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mod);
        AdsLoaded.loadbanner(this);
        tt=findViewById(R.id.ttl);
        tt.setText(modAdapter.ttl1);
        des=findViewById(R.id.descc);
        des.setText(modAdapter.desc);
        img=findViewById(R.id.imgshowed);
        Glide.with(ShowMod.this).load(modAdapter.img).into(img);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 101);

            }
        });
        SharedPreferences clkd = getSharedPreferences("cpahome", MODE_PRIVATE);
        boolean loadedd = clkd.getBoolean("cpah", true);
        if(loadedd){
            if(SplashActivity.showCpa){
                showCustomDialog();
            }
        }


    }
    private void showCustomDialog() {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        LottieAnimationView btn=dialogView.findViewById(R.id.buttonOkk);
        ImageView img=dialogView.findViewById(R.id.cpaimg);


        Glide.with(this).load(SplashActivity.CpaImage).into(img);
        TextView txt=dialogView.findViewById(R.id.titlecpa);
        txt.setText(SplashActivity.CpaTitle);
        TextView tt=dialogView.findViewById(R.id.soustitlecpa);
        tt.setText(SplashActivity.CpaSousTitle);
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        ImageView closebtn=dialogView.findViewById(R.id.closedialoge);
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(SplashActivity.CpaUrl));
                startActivity(intent);
                SharedPreferences l= getSharedPreferences("cpahome", MODE_PRIVATE);
                SharedPreferences.Editor coinsEdit = l.edit();
                coinsEdit.putBoolean("cpah", false);
                coinsEdit.apply();

            }
        });
        alertDialog.setCancelable(false);
    }

    private void downloadingImage() {
        ProgressDialog alertDialogBuilder = new ProgressDialog(ShowMod.this);
        alertDialogBuilder.show();
        alertDialogBuilder.setMessage("downloading MOD");
        alertDialogBuilder.setCancelable(false);
         Glide.with(this).asBitmap()
                .load(modAdapter.img)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Bitmap> transition) {
                        saveImage(resource,alertDialogBuilder);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

    }
    private void saveImage(Bitmap resource,ProgressDialog progressDialog) {
        String imageName= UUID.randomUUID()+".jpg";
        FileOutputStream fileOutputStream;
        String path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

        try {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
                ContentResolver cr=getContentResolver();
                ContentValues cv=new ContentValues();
                cv.put(MediaStore.MediaColumns.DISPLAY_NAME,imageName);
                cv.put(MediaStore.MediaColumns.MIME_TYPE,"image/*");
                cv.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_DOWNLOADS);
                Uri uri=cr.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI,cv);
                try  {
                    fileOutputStream =(FileOutputStream) cr.openOutputStream(Objects.requireNonNull(uri));
                    resource.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                    Objects.requireNonNull(fileOutputStream);
                }catch (Exception ex){

                }
            }else{
                File folder=new File(path);
                folder.mkdirs();
                File file=new File(folder,imageName);
                if(file.exists()){
                    file.delete();
                }
                fileOutputStream=new FileOutputStream(file);
                resource.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            }

            Toast.makeText(ShowMod.this,"Downloaded Successful",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(ShowMod.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(ShowMod.this, new String[] { permission }, requestCode);
        }
        else {
            AdsLoaded.rewardeLoaded(ShowMod.this, new AdsLoaded.inter() {
                @Override
                public void onfinished() {
                    downloadingImage();
                }
            });
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

       if (requestCode == 101) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ShowMod.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShowMod.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}