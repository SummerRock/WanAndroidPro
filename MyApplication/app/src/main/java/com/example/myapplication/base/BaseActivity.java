package com.example.myapplication.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

public abstract class BaseActivity<VM extends ViewModel> extends AppCompatActivity {
    protected VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        // 初始化 ViewModel
        viewModel = createViewModel();

        // 其他公共操作
        // ...
    }

    // 获取布局资源 ID
    protected abstract int getLayoutResourceId();

    // 创建 ViewModel
    protected abstract VM createViewModel();
}
