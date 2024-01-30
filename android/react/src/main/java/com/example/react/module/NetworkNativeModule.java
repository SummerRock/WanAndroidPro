package com.example.react.module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.mainModule.LogUtils;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class NetworkNativeModule extends ReactContextBaseJavaModule {

    public NetworkNativeModule(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "NetworkNativeModule";
    }

    @ReactMethod
    public void printLog() {
        LogUtils.d("network_test_log");
    }
}
