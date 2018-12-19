package com.jason.module_main.mvp.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jason.module_main.R;
import com.jason.module_main.mvp.contract.CityContract;
import com.jason.module_main.mvp.model.bean.CityBean;
import com.jason.module_main.mvp.presenter.CityPresenter;
import com.jason.module_main.mvp.ui.adapter.CityAdapter;
import com.jason.tools.base.BaseActivity;
import com.jason.tools.config.RouterConfig;
import com.jason.tools.config.SharedKey;
import com.jason.tools.utils.SharedUtil;
import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */
@Route(path = RouterConfig.CITY_PATH)
public class CityActivity extends BaseActivity<CityPresenter> implements CityContract.CityViewImpl {

    private Toolbar toolBar;
    private RecyclerView rv_city;

    @Override
    public CityPresenter createPresenter() {
        return new CityPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_city;
    }

    @Override
    public void initViews() {
        toolBar = findViewById(R.id.toolBar);
        setToolBarByBack(toolBar,"城市选择",null);
        rv_city = findViewById(R.id.rv_city);
        rv_city.setLayoutManager(new GridLayoutManager(this,4));
        rv_city.setHasFixedSize(true);
        presenter.requestCity();
    }

    @Override
    public void requestData() {

    }

    @Override
    public void getCity(final List<CityBean.City> list) {
        CityAdapter adapter = new CityAdapter(list);
        rv_city.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SharedUtil.getInstance().put(SharedKey.CITY_NAME,list.get(position).getName());
                SharedUtil.getInstance().put(SharedKey.CITY_ID,list.get(position).getId());
                finish();
            }
        });
    }
}
