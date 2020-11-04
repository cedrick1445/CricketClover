package com.example.mvvm.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.mvvm.R;

public class SplashScreen extends AppCompatActivity {

    ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgSplash = findViewById(R.id.imgSplash);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgSplash);
        Glide.with(this).load(R.raw.cricketsplash).into(imageViewTarget);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startMainActivity = new Intent(SplashScreen.this, Dashboard.class);
                startActivity(startMainActivity);
                finish();
            }
        }, 3000);
    }
}