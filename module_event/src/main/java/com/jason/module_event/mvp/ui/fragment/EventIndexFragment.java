package com.jason.module_event.mvp.ui.fragment;

import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jason.module_event.R;
import com.jason.module_event.mvp.ui.adapter.EventsPagerAdapter;
import com.jason.tools.base.BaseFragment;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.config.RouterConfig;

/**
 * Created by jason on 2018/12/12.
 */
@Route(path = RouterConfig.FRAGMENT_EVENT_PATH)
public class EventIndexFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RadioGroup rg_tags;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_event_index;
    }

    @Override
    public void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        rg_tags = view.findViewById(R.id.rg_tags);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new EventsPagerAdapter(getChildFragmentManager()));
        rg_tags.setOnCheckedChangeListener(listener);
    }

    @Override
    public void requestData() {

    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_future){

            }else if (checkedId == R.id.rb_week){

            }else if (checkedId == R.id.rb_weekend){

            }else if (checkedId == R.id.rb_today){

            }else if (checkedId == R.id.rb_tomorrow){

            }
        }
    };
}
