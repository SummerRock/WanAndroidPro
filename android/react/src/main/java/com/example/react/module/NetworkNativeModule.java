package com.example.react.module;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.common.mainModule.LogUtils;
import com.common.net.RetrofitManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;

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

    @ReactMethod
    public void requestNetworkCookieStr(
            String token,
            Promise promise) {
        try {
            WritableMap map = Arguments.createMap();
            RetrofitManager.getInstance().getCookie();
            map.putString("cookie", "test_cook");
            promise.resolve(map);
        } catch (IllegalViewOperationException e) {
            promise.reject("error_01", e);
        }
    }
}
