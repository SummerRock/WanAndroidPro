package com.example.myapplication.base.model;

import androidx.annotation.Nullable;

public class NetworkModel<T> {

    private static final String TAG = "Resource";
    private static final int DEFAULT_CODE = 0;

    public String errorMsg;
    public int errorCode;
    @Nullable
    public T data;
    private NetStatus netStatus;

    private NetworkModel(NetStatus netStatus, @Nullable T data, String message, int errorCode) {
        this.netStatus = netStatus;
        this.data = data;
        this.errorMsg = message;
        this.errorCode = errorCode;
    }

    public NetStatus getNetStatus() {
        if (netStatus == null) {
            if (data != null) {
                return NetStatus.SUCCESS;
            } else {
                return NetStatus.ERROR;
            }
        } else {
            return netStatus;
        }
    }

    public static <T> NetworkModel<T> loading() {
        return new NetworkModel<>(NetStatus.LOADING, null, null, DEFAULT_CODE);
    }

    public static <T> NetworkModel<T> failed(String message, int errorCode) {
        return new NetworkModel<>(NetStatus.ERROR, null, message, errorCode);
    }

    public enum NetStatus {
        SUCCESS,
        ERROR,
        LOADING,
        INVALID // 无效网络请求
    }
}
