package com.jason.module_flutter.ui;

import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jason.module_flutter.R;
import com.jason.module_flutter.channel.FinishChannel;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;

import io.flutter.facade.Flutter;
import io.flutter.view.FlutterView;

@Route(path = RouterConfig.FLUTTER_PATH)
public class MyFlutterActivity extends BaseActivity {


    private FrameLayout layout_flutter_container;


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_flutter;
    }

    @Override
    public void initViews() {
        layout_flutter_container = findViewById(R.id.layout_flutter_container);
        FlutterView flutterView = Flutter.createView(this,getLifecycle(),"route1");
        layout_flutter_container.addView(flutterView);
        //解决Android从原生跳到Flutter模块的黑屏问题
        FlutterView.FirstFrameListener[] listeners = new FlutterView.FirstFrameListener[1];
        listeners[0] = new FlutterView.FirstFrameListener() {
            @Override
            public void onFirstFrame() {
                layout_flutter_container.setVisibility(View.VISIBLE);
            }
        };
        flutterView.addFirstFrameListener(listeners[0]);
        FinishChannel.registerWith(flutterView.getPluginRegistry().registrarFor(FinishChannel.CHANNEL));
    }

    @Override
    public void requestData() {

    }
}
