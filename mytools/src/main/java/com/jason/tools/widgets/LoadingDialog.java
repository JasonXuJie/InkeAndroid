package com.jason.tools.widgets;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.jason.tools.R;

/**
 * Created by jason on 2018/11/21.
 */

public class LoadingDialog extends DialogFragment {

    private LottieAnimationView lottieView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        initViews(view);
        return view;
    }


    private void initViews(View view) {
        lottieView = view.findViewById(R.id.lottieView);
    }


    public void show(FragmentManager manager) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(this, "loadingView");
        transaction.commitAllowingStateLoss();
    }


    public void dismiss() {
        this.dismissAllowingStateLoss();
    }


    @Override
    public void onDestroy() {
        if (lottieView != null && lottieView.isAnimating()) {
            lottieView.cancelAnimation();
        }
        super.onDestroy();
    }
}
