package com.jason.module_my.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.jason.module_my.model.bean.DetailsBean;
import com.jason.module_my.model.callback.OnRequestCallBack;
import com.jason.module_my.model.repository.DetailsRepository;

import java.util.List;

/**
 * Created by jason on 2018/12/20.
 */

public class DetailsViewModel extends AndroidViewModel {

    private DetailsRepository repository;
    private MutableLiveData<List<DetailsBean>> data;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new DetailsRepository();
        data = new MutableLiveData<>();

    }


    public void requestDetails(String id){
        data.setValue(null);
        repository.requestDetails(id, new OnRequestCallBack<List<DetailsBean>>() {
            @Override
            public void getDataSuccess(List<DetailsBean> list) {
                data.setValue(list);
            }

            @Override
            public void getDataFailed(String msg) {

            }
        });
    }



    public MutableLiveData<List<DetailsBean>> getData(){
        return data;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
