package com.jason.tools.http;

import com.jason.tools.http.bean.JuheBaseResult;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jason on 2018/11/26.
 */

public abstract class BaseObserver<T> implements Observer<JuheBaseResult<T>> {


    public BaseObserver(){

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(JuheBaseResult<T> result) {
        if (result.getError_code()==0){
            onSuccess(result.getResult());
        }else {
            onFailed(result.getReason());
        }
    }

    @Override
    public void onError(Throwable e) {
       ExceptionHandler.handle(e);
    }

    @Override
    public void onComplete() {

    }


    public abstract void onSuccess(T data);
    public abstract void onFailed(String msg);
}
