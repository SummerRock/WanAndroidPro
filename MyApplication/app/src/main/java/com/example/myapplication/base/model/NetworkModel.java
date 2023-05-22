package com.example.myapplication.base.model;

import androidx.annotation.Nullable;

public class NetworkModel<T> {

    private static final String TAG = "Resource";

    public final String message;
    public final long code;
    @Nullable
    public final T data;
    public final NetStatus netStatus;

    private NetworkModel(NetStatus netStatus, @Nullable T data, String message, long code) {
        this.netStatus = netStatus;
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public enum NetStatus {
        SUCCESS,
        ERROR,
        LOADING,
        INVALID // 无效网络请求
    }
}
