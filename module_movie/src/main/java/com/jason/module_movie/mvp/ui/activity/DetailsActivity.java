package com.jason.module_movie.mvp.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jason.tools.config.RouterConfig;
import com.jason.tools.flutter.PageRouter;
import com.taobao.idlefish.flutterboost.containers.BoostFlutterActivity;
import java.util.HashMap;
import java.util.Map;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.GeneratedPluginRegistrant;


@Route(path = RouterConfig.DETAILS_PATH)
public class DetailsActivity extends BoostFlutterActivity {

    private String id = "";
    private String title = "";
    private String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("image");

    }

    @Override
    public String getContainerName() {
        return PageRouter.FLUTTER_MOVIE_DETAILS_URL;
    }

    @Override
    public Map getContainerParams() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("title", title);
        map.put("image",image);
        return map;
    }

    @Override
    public void onRegisterPlugins(PluginRegistry registry) {
        GeneratedPluginRegistrant.registerWith(registry);
    }
}
