package com.jason.module_main.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.jason.module_main.R;

/**
 * Created by jason on 2018/10/25.
 */

public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView lottieView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                finish();
            }
        },2500);
    }


    private void initViews(){
        lottieView = findViewById(R.id.lottieView);
    }


    @Override
    protected void onDestroy() {
        if (lottieView!=null&&lottieView.isAnimating()){
            lottieView.cancelAnimation();
        }
        super.onDestroy();
    }
}
