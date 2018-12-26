package com.jason.module_my.mvp.ui.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jason.module_my.R;
import com.jason.module_my.mvp.ui.activity.LuckActivity;
import com.jason.module_my.mvp.ui.activity.PairingActivity;
import com.jason.module_my.mvp.ui.activity.TodayInHistoryActivity;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;
import com.jason.tools.utils.PlatformUtil;

/**
 * Created by jason on 2018/10/26.
 */
@Route(path = RouterConfig.FRAGMENT_MY_PATH)
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView img_header;
    private TextView tv_horoscope;
    private TextView tv_constellation_matching;
    private TextView tv_chinese_zodiac_matching;
    private LinearLayout layout_function;

    private String[] titles = {"历史上的今天", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initViews(View view) {
        img_header = view.findViewById(R.id.img_header);
        tv_horoscope = view.findViewById(R.id.tv_horoscope);
        tv_constellation_matching = view.findViewById(R.id.tv_constellation_matching);
        tv_chinese_zodiac_matching = view.findViewById(R.id.tv_chinese_zodiac_matching);
        layout_function = view.findViewById(R.id.layout_function);
        img_header.setOnClickListener(this);
        tv_horoscope.setOnClickListener(this);
        tv_constellation_matching.setOnClickListener(this);
        tv_chinese_zodiac_matching.setOnClickListener(this);
        _renderList();
    }


    private void _renderList() {
        for (int i = 0; i < titles.length; i++) {
            final int index = i;
            View view = View.inflate(activity, R.layout.item_menu_function, null);
            LinearLayout item_layout = view.findViewById(R.id.item_layout);
            ImageView img_menu_icon = view.findViewById(R.id.img_menu_icon);
            TextView item_tv_title = view.findViewById(R.id.item_tv_title);
            View item_space = view.findViewById(R.id.item_space);
            item_tv_title.setText(titles[i]);
            if (PlatformUtil.isLollipop()) {
                item_layout.setBackgroundResource(R.drawable.ripple_item_my);
            }
            if (index % 3 == 0) {
                item_space.setVisibility(View.VISIBLE);
            }
            switch (index) {
                case 0:
                    img_menu_icon.setImageResource(R.drawable.my_icon_history);
                    break;
                case 1:
                    img_menu_icon.setImageResource(R.drawable.my_icon_tool);
                    break;
                case 2:
                    img_menu_icon.setImageResource(R.drawable.my_icon_share);
                    break;
                case 3:
                    img_menu_icon.setImageResource(R.drawable.my_icon_other);
                    break;
                case 4:
                    img_menu_icon.setImageResource(R.drawable.my_icon_setting);
                    break;
                case 5:
                    img_menu_icon.setImageResource(R.drawable.my_icon_about_us);
                    break;
            }
            item_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (index) {
                        case 0:
                            openActivityByNoParams(TodayInHistoryActivity.class);
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                }
            });
            layout_function.addView(view);
        }

    }

    @Override
    public void requestData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.img_header) {
            ARouter.getInstance().build("/my/test").navigation();
        }else if (id == R.id.tv_horoscope){
            openActivityByNoParams(LuckActivity.class);
        }else if (id == R.id.tv_constellation_matching){
            openPairingActivity("星座配对",1);
        }else if (id == R.id.tv_chinese_zodiac_matching){
            openPairingActivity("生肖配对",2);
        }
    }


    private void openPairingActivity(String title,int flag){
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putInt("flag",flag);
        openActivityByParams(PairingActivity.class,bundle);
    }
}
