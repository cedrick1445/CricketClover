package com.sports.cricketclover.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.kaiguanjs.utils.YQCUtils;
import com.sports.cricketclover.R;

public class SplashScreen extends AppCompatActivity {

    ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imgSplash = findViewById(R.id.imgSplash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgSplash);
        Glide.with(this).load(R.drawable.splashscreen).into(imageViewTarget);


        YQCUtils.splashAction(this, (version, downUrl) -> {
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent startMainActivity = new Intent(SplashScreen.this, Dashboard.class);
                startActivity(startMainActivity);
                finish();
            }, 3000);
        });
    }
}