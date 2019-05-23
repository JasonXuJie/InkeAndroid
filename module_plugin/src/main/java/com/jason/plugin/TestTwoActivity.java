package com.jason.plugin;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.jason.tools.base.BaseActivity;
import com.jason.tools.base.BasePresenter;

public class TestTwoActivity extends BaseActivity {

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test_two;
    }

    @Override
    public void initViews() {
        Button btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result","1111111");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        Button btn_back2 = findViewById(R.id.btn_back_two);
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("result","2222");
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }

    @Override
    public void requestData() {

    }
}
