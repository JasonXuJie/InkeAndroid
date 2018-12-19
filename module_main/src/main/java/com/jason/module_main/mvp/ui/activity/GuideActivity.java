package com.jason.module_main.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.didi.virtualapk.PluginManager;
import com.gyf.barlibrary.ImmersionBar;
import com.jason.module_main.R;
import com.jason.module_main.mvp.ui.adapter.GuideAdapter;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;
import com.jason.tools.utils.PermissionUtil;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by jason on 2018/10/26.
 */

public class GuideActivity extends BaseActivity implements View.OnClickListener{


    private ViewPager vp_guide;
    private Button btn_login;

    private int[] pics = {R.drawable.guide_one, R.drawable.guide_two, R.drawable.guide_three, R.drawable.guide_four};

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initViews() {
        ImmersionBar.with(this).reset().init();
        vp_guide = findViewById(R.id.vp_guide);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        vp_guide.addOnPageChangeListener(listener);
        vp_guide.setAdapter(new GuideAdapter(pics));
        loadPlugin();
        requestPermission();
    }

    @Override
    public void requestData() {

    }

    private void requestPermission(){
        PermissionUtil.requestEachPermission(this, new PermissionUtil.OnPermissionListener() {
            @Override
            public void granted() {
                Logger.e("granted");
            }

            @Override
            public void denied() {
                Logger.e("denied");
            }
        }, Manifest.permission.CAMERA);
    }

    private void loadPlugin(){
        final PluginManager pluginManager = PluginManager.getInstance(this);
        if (Environment.getExternalStorageDirectory()!=null){
            final File apk = new File(Environment.getExternalStorageDirectory(),"plugin.apk");
            if (apk.exists()){
                Logger.e("存在");
                PermissionUtil.requestEachPermission(this, new PermissionUtil.OnPermissionListener() {
                    @Override
                    public void granted() {
                        try {
                            pluginManager.loadPlugin(apk);
                            Logger.e("读取");
                        }catch (Exception e){
                            e.printStackTrace();
                            Logger.e("catch");
                        }
                    }

                    @Override
                    public void denied() {

                    }
                }, Manifest.permission.READ_EXTERNAL_STORAGE);
            }else {
                Logger.e("不存在");
            }
        }else{
            Logger.e("==null");
        }
    }


    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == pics.length - 1) {
                btn_login.setVisibility(View.VISIBLE);
            } else {
                btn_login.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_login){
            String packageName = "com.jason.plugin";
            if (PluginManager.getInstance(this).getLoadedPlugin(packageName)==null){
                openActivityByNoParams(MainActivity.class);
                Logger.e("为空");
            }else {
                Intent intent = new Intent();
                intent.setClassName(this,"com.jason.plugin.FirstActivity");
                startActivity(intent);
            }
            //openActivityByNoParams(PluginActivity.class);

        }
    }
}
