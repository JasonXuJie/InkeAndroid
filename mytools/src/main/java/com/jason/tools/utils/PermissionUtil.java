package com.jason.tools.utils;


//import android.support.v4.app.FragmentActivity;
//import com.tbruyelle.rxpermissions2.Permission;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//import io.reactivex.functions.Consumer;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * Created by jason on 2018/3/6.
 */

public class PermissionUtil {


    private PermissionUtil(){

    }


    public static void requestPermission(FragmentActivity activity,final OnPermissionListener listener,String...permission){
        RxPermissions permissions = new RxPermissions(activity);
        permissions.request(permission).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted){
                    listener.granted();
                }else {
                    listener.denied();
                }
            }
        });
    }



    public static void requestEachPermission(FragmentActivity activity, final OnPermissionListener listener, String...permission){
        final int length = permission.length;
        RxPermissions permissions = new RxPermissions(activity);
        permissions.requestEach(permission).subscribe(new Consumer<Permission>() {
            int count = 0;
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted){
                    //permission.name
                    count++;
                    if (count==length){
                        listener.granted();
                    }
                }else if (permission.shouldShowRequestPermissionRationale){
                    listener.denied();
                }else {
                    listener.denied();
                }
            }
        });
    }




    public interface OnPermissionListener{
        void granted();
        void denied();
    }



}
