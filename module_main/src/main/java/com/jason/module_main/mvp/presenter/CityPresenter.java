package com.jason.module_main.mvp.presenter;

import com.jason.module_main.mvp.contract.CityContract;
import com.jason.module_main.mvp.model.CityModel;
import com.jason.module_main.mvp.model.bean.CityBean;
import com.jason.module_main.mvp.model.callback.OnGetDataCallBack;
import com.jason.tools.base.BasePresenter;

import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */

public class CityPresenter extends BasePresenter<CityContract.CityViewImpl> implements CityContract.CityPresenterImpl {

    private CityModel model;

    public CityPresenter(){
        model = new CityModel();
    }


    @Override
    public void requestCity() {
        getView().showLoading();
        model.requestCitys(new OnGetDataCallBack<List<CityBean.City>>() {
            @Override
            public void getData(List<CityBean.City> data) {
                getView().getCity(data);
                getView().hideLoading();
            }
        });
    }
}
