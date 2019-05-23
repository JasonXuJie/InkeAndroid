package com.jason.module_main.mvp.ui.activity;

import android.widget.RadioGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.barlibrary.ImmersionBar;
import com.jason.module_main.R;
import com.jason.module_main.mvp.model.bean.MyWorker;
import com.jason.module_main.mvp.ui.adapter.FragmentAdapter;
import com.jason.module_main.mvp.ui.widgets.NoScrollViewPager;
import com.jason.module_main.service.KeepAliveService;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

/**
 * Created by jason on 2018/11/14.
 */

public class MainActivity extends BaseActivity {


    private NoScrollViewPager vp_main;
    private RadioGroup bottom_bar;
    private List<BaseFragment> list = new ArrayList<>();
    private final String TAG = "workManager";

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        vp_main = findViewById(R.id.vp_main);
        bottom_bar = findViewById(R.id.bottom_bar);
        BaseFragment moviesFragment = (BaseFragment) ARouter.getInstance().build(RouterConfig.FRAGMENT_MOVIE_PATH).navigation();
        BaseFragment eventFragment = (BaseFragment) ARouter.getInstance().build(RouterConfig.FRAGMENT_EVENT_PATH).navigation();
        BaseFragment newsFragment = (BaseFragment) ARouter.getInstance().build(RouterConfig.FRAGMENT_NEWS_PATH).navigation();
        BaseFragment myFragment = (BaseFragment) ARouter.getInstance().build(RouterConfig.FRAGMENT_MY_PATH).navigation();
        list.add(moviesFragment);
        list.add(eventFragment);
        list.add(newsFragment);
        list.add(myFragment);
        vp_main.setAdapter(new FragmentAdapter(getSupportFragmentManager(), list));
        bottom_bar.setOnCheckedChangeListener(listener);
        vp_main.setOffscreenPageLimit(list.size());
        //KeepAliveService.start(this);
        bottom_bar.check(R.id.tab_movie);
    }

    @Override
    public void requestData() {
       // executeWork();
    }



    /**
     * Workmanager轮询
     * */
    private void executeWork(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                //.setRequiresDeviceIdle(true)
                .build();
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class,10, TimeUnit.SECONDS)
                .addTag(TAG)
                .setConstraints(constraints)
                .build();
        WorkManager manager = WorkManager.getInstance();
        if (manager!=null){
            manager.enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.REPLACE,request);
        }
        //manager.cancelAllWork();

    }


    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.tab_movie) {
                ImmersionBar.with(MainActivity.this).reset().fitsSystemWindows(false).init();
                vp_main.setCurrentItem(0, false);
            } else if (checkedId == R.id.tab_event) {
                ImmersionBar.with(MainActivity.this).statusBarColor(R.color.white).barAlpha(0.3f).fitsSystemWindows(true).init();
                vp_main.setCurrentItem(1, false);
            } else if (checkedId == R.id.tab_news) {
                ImmersionBar.with(MainActivity.this).statusBarColor(R.color.C_0099fd).barAlpha(0.3f).fitsSystemWindows(true).init();
                vp_main.setCurrentItem(2, false);
            } else if (checkedId == R.id.tab_my) {
                ImmersionBar.with(MainActivity.this).statusBarColor(R.color.C_0099fd).barAlpha(0.3f).fitsSystemWindows(true).init();
                vp_main.setCurrentItem(3, false);
            }
        }
    };
}
