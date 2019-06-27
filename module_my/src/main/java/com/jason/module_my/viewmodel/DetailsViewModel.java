package com.jason.module_my.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.jason.module_my.model.bean.DetailsResult;
import com.jason.module_my.model.callback.OnRequestCallBack;
import com.jason.module_my.model.repository.DetailsRepository;

import java.util.List;

/**
 * Created by jason on 2018/12/20.
 */

public class DetailsViewModel extends AndroidViewModel {

    private DetailsRepository repository;
    private MutableLiveData<List<DetailsResult>> data;

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new DetailsRepository();
        data = new MutableLiveData<>();

    }


    public void requestDetails(String id){
        data.setValue(null);
        repository.requestDetails(id, new OnRequestCallBack<List<DetailsResult>>() {
            @Override
            public void getDataSuccess(List<DetailsResult> list) {
                data.setValue(list);
            }

            @Override
            public void getDataFailed(String msg) {

            }
        });
    }



    public MutableLiveData<List<DetailsResult>> getData(){
        return data;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
