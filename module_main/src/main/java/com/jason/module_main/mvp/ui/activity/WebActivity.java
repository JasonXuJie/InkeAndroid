package com.jason.module_main.mvp.ui.activity;

import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jason.module_main.R;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;
import com.jason.tools.utils.StringUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebConfig;

/**
 * Created by jason on 2018/11/21.
 */
@Route(path = RouterConfig.WEB_PATH)
public class WebActivity extends BaseActivity {


    private FrameLayout layout_web_container;
    private AgentWeb mAgentWeb;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initViews() {
        String url = getIntent().getStringExtra("url");
        layout_web_container = findViewById(R.id.layout_web_container);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(layout_web_container,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebViewClient(webViewClient)
                .setWebChromeClient(chromeClient)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    public void requestData() {

    }


    private WebViewClient webViewClient = new WebViewClient(){

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    };

    private WebChromeClient chromeClient = new WebChromeClient(){
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (!StringUtil.isEmpty(title)){

            }
        }
    };


    @Override
    protected void onResume() {
        if (mAgentWeb!=null)mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mAgentWeb!=null)mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        if (mAgentWeb!=null)mAgentWeb.getWebLifeCycle().onDestroy();
        AgentWebConfig.clearDiskCache(this);
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        if (mAgentWeb!=null&&mAgentWeb.back()){
            mAgentWeb.back();
            return;
        }
        super.onBackPressed();
    }
}
