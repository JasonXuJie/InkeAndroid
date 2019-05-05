package com.jason.module_my.mvp.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.jason.module_my.R;
import com.jason.module_my.model.bean.DetailsBean;
import com.jason.module_my.mvp.ui.adapter.PicAdapter;
import com.jason.module_my.viewmodel.DetailsViewModel;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;
import com.jason.tools.netimage.ImageLoader;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by jason on 2018/12/20.
 */

public class HistoryDetailsActivity extends BaseActivity implements View.OnClickListener{


    private Toolbar toolBar;
    private TextView tv_content;
    private ImageView img_pic;
    private ViewPager vp_pics;
    private LinearLayout layout_photo;
    private PhotoView photoView;
    private DetailsBean data;

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_details;
    }

    @Override
    public void initViews() {
        String title = getIntent().getStringExtra("title");
        toolBar = findViewById(R.id.toolBar);
        img_pic = findViewById(R.id.img_pic);
        tv_content = findViewById(R.id.tv_content);
        vp_pics = findViewById(R.id.vp_pics);
        layout_photo = findViewById(R.id.layout_photo);
        photoView = findViewById(R.id.photoView);
        setToolBarByBack(toolBar, title, null);
        img_pic.setOnClickListener(this);
        layout_photo.setOnClickListener(this);
    }

    @Override
    public void requestData() {
        String id = getIntent().getStringExtra("id");
        Logger.e(id);
        DetailsViewModel viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        viewModel.requestDetails(id);
        viewModel.getData().observe(this, new Observer<List<DetailsBean>>() {
            @Override
            public void onChanged(@Nullable List<DetailsBean> detailsBeans) {
                if (detailsBeans == null) {
                    showLoading();
                } else {
                    hideLoading();
                    data = detailsBeans.get(0);
                    if (data.getPicUrl().size() == 1) {
                        img_pic.setVisibility(View.VISIBLE);
                        ImageLoader.loadImage(HistoryDetailsActivity.this, data.getPicUrl().get(0).getUrl(), img_pic);
                    } else {
                        vp_pics.setVisibility(View.VISIBLE);
                        PicAdapter adapter = new PicAdapter(data.getPicUrl());
                        vp_pics.setAdapter(adapter);
                        adapter.setOnItemClickListener(listener);
                    }
                    tv_content.setText(data.getContent());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.img_pic){
            layout_photo.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(HistoryDetailsActivity.this, data.getPicUrl().get(0).getUrl(), photoView);
        }else if (id == R.id.layout_photo){
            layout_photo.setVisibility(View.GONE);
        }
    }

    private PicAdapter.OnPicItemListener listener  = new PicAdapter.OnPicItemListener() {
        @Override
        public void onClick(String url, int position) {
            layout_photo.setVisibility(View.VISIBLE);
            ImageLoader.loadImage(HistoryDetailsActivity.this, url, photoView);
        }
    };
}
