package com.jason.module_event.mvp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_event.R;
import com.jason.module_event.mvp.contract.EventsContract;
import com.jason.module_event.mvp.model.bean.EventBean;
import com.jason.module_event.mvp.presenter.EventsPresenter;
import com.jason.module_event.mvp.ui.adapter.EventsAdapter;
import com.jason.tools.base.BaseLazyFragment;
import com.jason.tools.config.RouterConfig;
import com.orhanobut.logger.Logger;
import java.util.List;

/**
 * Created by jason on 2018/11/14.
 */

public class EventFragment extends BaseLazyFragment<EventsPresenter> implements View.OnClickListener, EventsContract.EventsViewImpl {

    private RecyclerView rv_events;
    private FloatingActionButton fab_top;


    public static EventFragment newInstance(String tag) {
        Bundle bundle = new Bundle();
        bundle.putString("flag", tag);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public EventsPresenter createPresenter() {
        return new EventsPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_event;
    }


    @Override
    public void lazyInitViews(View view) {
        rv_events = view.findViewById(R.id.rv_events);
        fab_top = view.findViewById(R.id.fab_top);
        rv_events.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        rv_events.setHasFixedSize(true);
        fab_top.setOnClickListener(this);
    }

    @Override
    public void lazyRequestData() {
        Bundle bundle = getArguments();
        String type = bundle.getString("flag");
        presenter.requestEvents("108296", type);
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_top) {
            rv_events.scrollToPosition(0);
        }
    }


    @Override
    public void getEvents(final List<EventBean.EventsBean> list) {
        EventsAdapter adapter = new EventsAdapter(list);
        rv_events.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ARouter.getInstance().build(RouterConfig.WEB_PATH).withString("url", list.get(position).getUrl()).navigation();
            }
        });
       // adapter.setHeaderView(getHeaderView());
    }


    private View getHeaderView(){
        View headerView = View.inflate(activity,R.layout.header_events,null);
        return headerView;
    }
}
