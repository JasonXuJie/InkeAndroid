package com.jason.module_main.mvp.ui.activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jason.module_main.R;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;

/**
 * 组件化创建快捷方式中间件
 * 静态文件拿不到对应包下的activity
 * **/
public class ShortcutActivity extends BaseActivity {

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shortcut;
    }

    @Override
    public void initViews() {
        String flag = getIntent().getStringExtra("flag");
        switch (flag) {
            case "search":
                ARouter.getInstance().build(RouterConfig.SEARCH_PATH).navigation();
                break;
            case "today":
                ARouter.getInstance().build(RouterConfig.TODAY_PATH).navigation();
                break;
            default:
                finish();
        }
        finish();
    }

    @Override
    public void requestData() {

    }
}
