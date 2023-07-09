package com.example.myapplication.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.common.mainModule.CommonUtils;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

public class MainActivity extends BaseActivity<VMMain> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected VMMain createViewModel() {
        return null;
    }

    public void showToast(View view) {
        Toast.makeText(this, CommonUtils.getString(), Toast.LENGTH_LONG).show();
        throw new IllegalArgumentException("Test Crash");
    }
}
