package com.jason.module_main.mvp.contract;

import com.jason.module_main.mvp.model.bean.CityBean;
import com.jason.tools.base.IBaseView;

import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */

public class CityContract {

    public interface CityViewImpl extends IBaseView{

        void getCity(List<CityBean.City> list);
    }


    public interface CityPresenterImpl{
        void requestCity();
    }
}
