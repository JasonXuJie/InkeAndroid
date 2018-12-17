package com.jason.module_my.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.jason.module_my.model.callback.OnRequestCallBack;
import com.jason.module_my.model.repository.HistoryRepository;
import com.jason.tools.http.bean.HistoryContent;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by jason on 2018/11/27.
 */

public class HistoryListViewModel extends AndroidViewModel {

    private MutableLiveData<List<HistoryContent>> datas;
    private MutableLiveData<String> errMsg;
    private HistoryRepository repository;

    public HistoryListViewModel(@NonNull Application application) {
        super(application);
        datas = new MutableLiveData<>();
        datas.setValue(null);
        errMsg = new MutableLiveData<>();
        repository = new HistoryRepository();
        repository.requestData(new OnRequestCallBack<List<HistoryContent>>() {


            @Override
            public void getDataSuccess(List<HistoryContent> data) {
                datas.setValue(data);
            }

            @Override
            public void getDataFailed(String msg) {
                errMsg.setValue(msg);
            }

        });
    }

    public MutableLiveData<List<HistoryContent>> getList(){
        return datas;
    }

    public MutableLiveData<String> getErrMsg(){
        return errMsg;
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
