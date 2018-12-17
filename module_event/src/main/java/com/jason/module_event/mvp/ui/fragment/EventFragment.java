package com.jason.module_event.mvp.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.jason.module_event.R;
import com.jason.module_event.mvp.ui.adapter.EventsAdapter;
import com.jason.tools.base.BaseLazyFragment;
import com.jason.tools.base.BasePresenter;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2018/11/14.
 */

public class EventFragment extends BaseLazyFragment implements View.OnClickListener{

    private RecyclerView rv_events;
    private FloatingActionButton fab_top;

    private List<String> data = new ArrayList<>();



    public static EventFragment newInstance(String tag){
        Bundle bundle = new Bundle();
        bundle.putString("flag",tag);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_event;
    }


    @Override
    public void lazyInitViews(View view) {
        rv_events = view.findViewById(R.id.rv_events);
        fab_top = view.findViewById(R.id.fab_top);
        rv_events.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        rv_events.setHasFixedSize(true);
        fab_top.setOnClickListener(this);
    }

    @Override
    public void lazyRequestData() {
        Bundle bundle = getArguments();
        String flag = bundle.getString("flag");
        Logger.e(flag);
        for (int i=0;i<50;i++){
            data.add("item"+i);
        }
        rv_events.setAdapter(new EventsAdapter(data));
    }

    @Override
    public void onInVisible() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.fab_top){
            rv_events.scrollToPosition(0);
        }
    }
}
