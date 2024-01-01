package com.example.monitor;

import android.content.Context;

import androidx.annotation.NonNull;

public class FloatWindowManager {

    // 私有构造函数，防止外部实例化
    private FloatWindowManager() {
        // 初始化操作
    }

    // 静态内部类，用于延迟加载单例实例
    private static class SingletonHolder {
        private static final FloatWindowManager INSTANCE = new FloatWindowManager();
    }

    private FPSOverlayView fpsOverlayView;

    // 公共方法，提供获取单例实例的接口
    public static FloatWindowManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void showOverlayView(@NonNull Context context) {
        if (fpsOverlayView == null) {
            fpsOverlayView = new FPSOverlayView(context);
        }
    }

    public void hideOverlayView() {
        if (fpsOverlayView != null) {
            fpsOverlayView.hide();
            fpsOverlayView = null;
        }
    }

}
