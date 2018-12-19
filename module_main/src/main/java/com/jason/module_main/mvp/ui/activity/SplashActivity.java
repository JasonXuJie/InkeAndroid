package com.jason.module_main.mvp.ui.activity;



import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;
import com.gyf.barlibrary.ImmersionBar;
import com.jason.module_main.R;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;

/**
 * Created by jason on 2018/10/25.
 */

public class SplashActivity extends BaseActivity {


    private LottieAnimationView lottieView;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        setTheme(R.style.AppTheme);
        return R.layout.activity_splash;
    }

    @Override
    public void initViews() {
        ImmersionBar.with(this).reset().init();
        lottieView = findViewById(R.id.lottieView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivityByNoParams(GuideActivity.class);
                finish();
            }
        }, 2500);
    }

    @Override
    public void requestData() {

    }


    @Override
    protected void onDestroy() {
        if (lottieView != null && lottieView.isAnimating()) {
            lottieView.cancelAnimation();
        }
        super.onDestroy();
    }
}
