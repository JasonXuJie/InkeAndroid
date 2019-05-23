package com.jason.module_main.mvp.ui.activity;



import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;
import com.gyf.barlibrary.ImmersionBar;
import com.jason.module_main.R;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.SharedKey;
import com.jason.tools.utils.PlatformUtil;
import com.jason.tools.utils.SharedUtil;
import java.util.ArrayList;
import java.util.List;

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
                boolean isFirst = (boolean) SharedUtil.getInstance().get(SharedKey.IS_FIRST_LOGIN,true);
                Class<?> cls = isFirst==true?GuideActivity.class:MainActivity.class;
                openActivityByNoParams(cls);
                finish();
            }
        }, 2500);
    }

    @Override
    public void requestData() {
        initShortCut();
    }


    private void initShortCut(){
        if (PlatformUtil.isN()){
            Intent intent1 = new Intent(this, ShortcutActivity.class);
            intent1.putExtra("flag","search");
            intent1.setAction(Intent.ACTION_VIEW);
            ShortcutManager manager = getSystemService(ShortcutManager.class);
            List<ShortcutInfo> list = new ArrayList<>();
            ShortcutInfo searchInfo = new ShortcutInfo.Builder(this,"search")
                    .setShortLabel(getString(R.string.shortcut_search_short))
                    .setLongLabel(getString(R.string.shortcut_search_long))
                    .setIcon(Icon.createWithResource(this, R.drawable.app_icon))
                    .setIntent(intent1)
                    .build();
            list.add(searchInfo);
            Intent intent2 = new Intent(this, ShortcutActivity.class);
            intent2.setAction(Intent.ACTION_VIEW);
            intent2.putExtra("flag","today");
            ShortcutInfo todayInfo = new ShortcutInfo.Builder(this,"today")
                    .setShortLabel(getString(R.string.shortcut_today_short))
                    .setLongLabel(getString(R.string.shortcut_today_long))
                    .setIcon(Icon.createWithResource(this, R.drawable.app_icon))
                    .setIntent(intent2)
                    .build();
            list.add(todayInfo);
            manager.setDynamicShortcuts(list);
        }
    }




    @Override
    protected void onDestroy() {
        if (lottieView != null && lottieView.isAnimating()) {
            lottieView.cancelAnimation();
        }
        super.onDestroy();
    }
}
